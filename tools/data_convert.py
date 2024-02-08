
import argparse
import shutil
from pathlib import Path

import orjson
import xmltodict
from hypy_utils import write
from hypy_utils.tqdm_utils import pmap


def convert_one(file: Path):
    # Get path relative to source
    rel = file.relative_to(src)

    # If path is one-level under StreamingAssets, ignore it (e.g. StreamingAssets/A000/Data.xml)
    if len(rel.parts) <= 2:
        return

    # Read xml
    xml = xmltodict.parse(file.read_text())

    # There should only be one root element, expand it
    assert len(xml) == 1, f'Expected 1 root element, got {len(xml)}'
    xml = xml[list(xml.keys())[0]]

    # Remove @xmlns:xsi and @xmlns:xsd
    if '@xmlns:xsi' in xml:
        del xml['@xmlns:xsi']
    if '@xmlns:xsd' in xml:
        del xml['@xmlns:xsd']

    # Generate target file path
    # Ignore the first segment of the relative path, and append to the destination
    # Also collapse the single-item directory into the filename
    # e.g. {src}/A000/music/music000001/Music.xml -> {dst}/music/000001.json
    target = dst / '/'.join(rel.parts[1:-2])
    file_id = ''.join(filter(str.isdigit, rel.parts[-2]))
    target = target / f'{file_id}.json'

    # Create directories if they don't exist
    target.parent.mkdir(parents=True, exist_ok=True)

    # Write json
    write(target, orjson.dumps(xml))


if __name__ == '__main__':
    agupa = argparse.ArgumentParser()
    agupa.add_argument('source', type=str, help='Package/Sinmai_Data/StreamingAssets directory')
    agupa.add_argument('destination', type=str, help='Directory to extract to')
    args = agupa.parse_args()

    src = Path(args.source)
    dst = Path(args.destination)

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


