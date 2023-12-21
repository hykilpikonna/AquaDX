#!/usr/bin/env python3
import argparse
import shlex
from subprocess import check_call, check_output

import openai
# Readline is used to fix left/right arrow keys in input(), do not remove as unused
import readline

my_base = 'master'


def gen_merge_msg(commits: str):
    openai_example_commits = """
        0d422120 - [API] Add a feature to retrieve user photos.
        4a64895e - [api] Add maimai2 userPhoto API
        e271cb45 - [API] Add a feature to retrieve user photos.
        0bf54e66 - [API] Let web music list read from database
        0913ef20 - [api] Add maimai2 userPhoto API
        7cc9fb11 - Revert "New API to return user photos."
        9c51b1e0 - Revert "Add API - Get user photos (mai)"
        ba1f4589 - New API to return user photos.
        e7848cb9 - Add API - Get user photos (mai)
        """
    openai_example_response = "Add user photos feature for maimai2"
    openai_prompt = ("I just merged the following branch, what commit message can best summarize the changes below? "
                     "Please make sure it is less than 100 characters long.\n\n")

    complete = openai.ChatCompletion.create(
        model="gpt-4",
        messages=[
            {"role": "system",
             "content": "You are a senior software engineer helping with maintaining a game server repository."},
            {"role": "user",
             "content": f"{openai_prompt}{openai_example_commits}"},
            {"role": "assistant",
             "content": f'"{openai_example_response}"'},
            {"role": "user",
             "content": f"{openai_prompt}{commits}"},
        ]
    )

    m = complete.choices[0].message.content
    return m.strip().strip('"')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Helper for merging remote sibling repos')
    parser.add_argument('url', type=str, help='Remote repo url')
    parser.add_argument('-b', '--branch', type=str, default='master', help='Branch to merge')
    parser.add_argument('-m', '--msg', type=str, default=None, help='Merge commit message')
    args = parser.parse_args()
    url = args.url

    print(f'Merging {url}...')

    # Fetch head branch
    print()
    print('Fetching head branch...')
    check_call(f'git -c http.sslVerify=false fetch {url} {args.branch}', shell=True)

    # Get the list of commits from the branch
    commits = check_output(f'git log --pretty=format:"%H - %s" {my_base}..FETCH_HEAD', shell=True).decode()
    commits = [c.split(' - ', 1) for c in commits.split('\n')]
    commits = [c for c in commits if len(c) == 2]
    commits = [f"{sha[:8]} - {msg}" for sha, msg in commits]
    commits = '\n'.join(commits)

    print("Commits:")
    print(commits)

    # Get the list of authors from the branch
    authors = check_output(f'git log --pretty=format:"%an <%ae>" {my_base}..FETCH_HEAD', shell=True).decode()
    authors = set(authors.split('\n'))
    authors = "\n".join(f"Co-authored-by: {a}" for a in authors)

    print("\nAuthors:")
    print(authors)

    # Create a merge commit message
    owner = url.split('/')[-2]
    repo = url.split('/')[-1].split('.')[0]

    # If there isn't a merge message, create one by asking ChatGPT to summarize the commits
    msg = args.msg
    if not msg:
        while True:
            msg = input('\nPlease enter a merge message (or leave blank to generate one): ').strip()
            if msg:
                break

            msg = gen_merge_msg(commits)
            print(f'Generated message: {msg}')
            if input('Is this okay? [y/N] ').lower() == 'y':
                break

    # Merge head branch
    print('\nMerging fetch_head...')
    check_call([*shlex.split('git merge FETCH_HEAD --no-ff --no-edit'),
                '-m', f"Merge {owner}/{repo} : {msg}",
                '-m', f"{commits}\n\n{authors}"])

    # Push
    assert input('\nPush? [Enter/N]') == ""
    check_call('git push', shell=True)
