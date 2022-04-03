# CONTRIBUTING
Thanks for your interest in improving Aqua server, and huge welcome! This document will guide you how to get started.

# Before you start

* We (or at least I, the fork maintainer) will not care where you come from or what kind of scene you involve with as long as you are in good faith.
* This fork might have n-0 or even n+1 support from time to time. If you can't agree with this, please refrain from get involve with this project.
* We **will not help you to get game itself**. This includes sharing a patched or original binary. Please don't ask or give guidance about it within this repository. This is bare minimum for our safety and long run possibility of this project.

# Getting started

* Please make sure you read README.md and documents in /docs folder.
* Make sure you have account for this gitlab instance.

# Steps to follow

* Open a issue: if you found a bug or want to add or suggest new feature, try open a issue first so we can know about the problem and start discussion.
  * Include some kind of information (for example: error log if a bug; what you trying to achieve if a new feature)
  * This step is not essential but will make thing much easier later on. (PR)
  * If you are end user (not a developer), it ends in here. Thanks!

* Fork it and make a new branch where you start working on it.
  * Please make a branch per PR if you are thinking about making multiple PRs. Still, we can cherry-pick even it's not but..

* Start working
  * We don't have any code formatting guide or rule. As long as code runs fine and readable enough, good to go.
  * Make commits of logical and atomic units. Please DO NOT make a commit message too general like "WIP - 1" or "Work".

* Test if it works
  * Database changes: default database testing is sqlite only. If you are not sure or can't test with MariaDB or MySQL, just leave a message about it in the PR.
  * Game endpoint changes: Although aqua have testing process while building and packaging, this probably won't do much for the game so our recommendation is run through a game at least once (from entry to saving).
  * Server API / feature / bug fix: Just test at least once the way you want.

* Make a PR
  * Thanks! We will get you know when it's ready.

We appreciate your time. Contribution is what makes open source project interesting and keep working.