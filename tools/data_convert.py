
import argparse
import os
import shutil
from pathlib import Path

import orjson
import xmltodict
from hypy_utils import write
from hypy_utils.tqdm_utils import pmap
from wand.image import Image


def convert_path(file: Path):
    # Get path relative to source
    rel = file.relative_to(src)

    # If path is one-level under StreamingAssets, ignore it (e.g. StreamingAssets/A000/Data.xml)
    if len(rel.parts) <= 2:
        return

    # Generate target file path
    # Ignore the first segment of the relative path, and append to the destination
    # Also collapse the single-item directory into the filename
    # e.g. {src}/A000/music/music000001/Music.xml -> {dst}/music/000001.json
    target = dst / '/'.join(rel.parts[1:-2])
    file_id = ''.join(filter(str.isdigit, rel.parts[-2]))
    file_id = file_id.zfill(6)
    target = target / f'{file_id}.json'

    return target


def convert_one(file: Path):
    target = convert_path(file)
    if target is None:
        return

    # Read xml
    try:
        xml = xmltodict.parse(file.read_text())
    except Exception as e:
        print(f'Error parsing {file}: {e}')
        return

    # There should only be one root element, expand it
    assert len(xml) == 1, f'Expected 1 root element, got {len(xml)}'
    xml = xml[list(xml.keys())[0]]

    # Remove @xmlns:xsi and @xmlns:xsd
    if '@xmlns:xsi' in xml:
        del xml['@xmlns:xsi']
    if '@xmlns:xsd' in xml:
        del xml['@xmlns:xsd']

    # Write json
    write(target, orjson.dumps(xml))


def convert_dds(file: Path):
    target = convert_path(file)
    if target is None:
        return

    # Convert dds to jpg
    try:
        with Image(filename=str(file)) as img:
            img.format = 'jpeg'
            img.save(filename=str(target.with_suffix('.png')))
    except Exception as e:
        print(f'Error converting {file}: {e}')
        return


def get(d: dict, *keys: str):
    """
    Get the first key that exists in the dictionary

    :param d: Dictionary
    :param keys: Recursive key in the format of keya.keyb.keyc...
    """
    for k in keys:
        ks = k.split('.')
        cd = d
        while len(ks) > 0:
            cd = cd.get(ks.pop(0))
            if cd is None:
                break
        if cd is not None:
            return cd
    return None


def combine_music_mai2():
    # Read all music json files
    music_files = list(dst.rglob('music/*.json'))
    print(f'> Found {len(music_files)} music files')
    jsons = [orjson.loads(f.read_text()) for f in music_files]

    # Combine all music
    combined = {d['name']['id']: {
        'name': d['name']['str'],
        'ver': d.get('version') or d.get('releaseTagName')['str'],
        'composer': d['artistName']['str'],
        'genre': d['genreName']['str'] or d['genreNames'],
        'bpm': int(d['bpm']),
        'lock': f"{d['lockType']} {d['subLockType']}",
        'notes': [{
            'lv': int(n['level']) + (int(n['levelDecimal']) / 10.0),
            'designer': n['notesDesigner']['str'],
            'lv_id': n['musicLevelID'],
            'notes': int(n['maxNotes']),
        } for n in d['notesData']['Notes'] if n['isEnable'] != 'false']
    } for d in jsons}

    # Write combined music
    write(dst / '00/all-music.json', orjson.dumps(combined))


def combine_music_chu3():
    # Read all music json files
    music_files = list(dst.rglob('music/*.json'))
    print(f'> Found {len(music_files)} music files')
    jsons = [orjson.loads(f.read_text()) for f in music_files]

    # Combine all music
    combined = {}
    for d in jsons:
        combined[d['name']['id']] = {
            'name': d['name']['str'],
            'ver': d['releaseTagName']['str'],
            'composer': d['artistName']['str'],
            'genre': get(d, 'genreName.list.StringID.str'),
            'lock': d['firstLock'],
            'notes': [{
                'lv': int(n['level']) + (int(n['levelDecimal']) / 100.0),
                'designer': n.get('notesDesigner'),
                'lv_id': n['type']['id'],
            } for n in d['fumens']['MusicFumenData'] if n['enable'] != 'false']
        }

    # Write combined music
    write(dst / '00/all-music.json', orjson.dumps(combined))


if __name__ == '__main__':
    agupa = argparse.ArgumentParser()
    # Or chusan/App/data
    agupa.add_argument('source', type=str, help='Package/Sinmai_Data/StreamingAssets directory')
    agupa.add_argument('destination', type=str, help='Directory to extract to')
    agupa.add_argument('-g', '--game', type=str, help='Game to convert', default='mai2', choices=['mai2', 'chu3'])
    args = agupa.parse_args()

    src = Path(args.source)
    dst = Path(args.destination)

    # Special post-convert command to relocate stuff
    if args.source == 'post-convert':
        ori = dst
        dst = dst.parent

        # In assetbundle/dir, move each XXX_{id}_XXX.png to assetbundle/dir/{id}.png
        for d in os.listdir(dst / 'assetbundle'):
            d = dst / 'assetbundle' / d
            if not d.is_dir():
                continue

            print(f'Relocating {d}')
            for file in d.rglob('*.png'):
                id = ''.join(filter(str.isdigit, file.stem))
                shutil.move(file, d / f'{id}.png')

        exit(0)

    # Assert that A000 exists in the source directory
    assert (src / 'A000').exists(), f'{src}/A000 does not exist'

    # Assert that target directory does not exist
    if dst.exists():
        if input(f'{dst} already exists, delete? (y/n): ') == 'y':
            print(f'Deleting {dst}')
            shutil.rmtree(dst)

    # Find all xml files in the source directory
    files = list(src.rglob('*.xml'))
    print(f'Found {len(files)} xml files')

    # Multithreaded map
    pmap(convert_one, files, desc='Converting', unit='file', chunksize=50)
    print('> Finished converting')

    # Find all .dds files in the source A000 directory
    dds_files = list(src.rglob('*.dds'))
    print(f'Found {len(dds_files)} dds files')

    # Convert and copy dds files (CPU-intensive)
    pmap(convert_dds, dds_files, desc='Converting DDS', unit='file', chunksize=50, max_workers=os.cpu_count() - 2)
    print('> Finished converting DDS')

    # Convert all music
    print('Combining music')
    if args.game == 'mai2':
        combine_music_mai2()
    if args.game == 'chu3':
        combine_music_chu3()



