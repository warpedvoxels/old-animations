# Contributing to Old Animations

First off, thank you for taking time for contributing to the Old Animations Project. The following is a set of guidelines
for contributing to the Old Animations Project, not necessarily rules. Apply them according to the changes made and feel
free to propose changes in future pull requests.

We are usually lenient with all submitted pull requests, but you can follow these guidelines to make the approval
process go more smoothly. Please make sure that the code compiles before pull requesting.

> Use the main branch for development.

## Table of contents

* *(Here)* [Table of contents](#table-of-contents)
* [Code of conduct](#code-of-conduct)
* [Fork with personal accounts](#fork-with-personal-accounts)
* [Setting up environment](#setting-up-environment)
* [Coding conventions](#coding-conventions)

## Code of conduct

This project and everyone participating in it is governed by [Contributor Covenant Code of Conduct][code_of_conduct_url]
. Please report unacceptable behavior to the [GitHub Team][github_team].

## Fork with personal accounts

We will modify your pull request, whether it is a rebase or to take care of any minor problems we might have.

Unfortunately, if you use an organization for your pull request, it prevents we from modifying it. This requires us
to manually merge your pull request, resulting in us closing the pull request instead of marking it as merged. We prefer
to mark a repository as merged, as this way we would notify the author of the updates, and we would organize ourselves
better if we can change the code freely.

## Coding conventions

Start reading our code, and you will get the hang of it. We optimize for readability:

1. We indent using four spaces.

2. Every line should have a maximum of 120 characters, but it is fine to go over as long as it does not hurt
   readability.

3. We do not use spaces before new type definitions (generic parameters).

4. Every subproject must have one responsibility, you can use recursive subprojects as you need.

5. Every function and class name should follow the **C**amel**C**ase pattern.

6. File names should follow **C**amel**C**ase if there is a class or interface inside them, otherwise you can follow
   the **c**amel**C**ase pattern.

7. No parenthesis before `if`, `when`, `try`, `catch` and other statements.

## Setting up environment

1. Create a fork on GitHub. <br/>
   ![Fork Alt Text](assets/fork.png)

2. Clone the fork you did.

```
$ git clone https://github.com/Example/old-animations.git
Cloning into 'old-animations'...
```

> Replace `Example` with your GitHub username.

3. Go to the generated folder.

```
$ cd ./old-animations
```

4. Configure upstream remote to keep your fork updated.

```
$ git remote add upstream https://github.com/nekkan/old-animations.git
```

5. Create a branch based on `upstream/main` (or `upstream/gh-pages` for the GitHub Page).

```
$ git fetch upstream main
From https://github.com/nekkan/old-animations
 * branch              main -> FETCH_HEAD
 * [new branch]        main -> upstream/development

$ git checkout -b my-branch-wow upstream/main
Switched to a new branch 'my-branch-wow'
```

Check out also:

* [Syncing a fork][syncing_a_fork]

[code_of_conduct_url]: https://github.com/nekkan/old-animations/blob/master/CODE_OF_CONDUCT.md

[syncing_a_fork]: https://docs.github.com/en/free-pro-team@latest/github/collaborating-with-issues-and-pull-requests/syncing-a-fork

[github_team]: https://docs.github.com/pt/free-pro-team@latest/github/building-a-strong-community/reporting-abuse-or-spam
