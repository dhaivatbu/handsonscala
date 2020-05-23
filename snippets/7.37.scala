@ val gitStatus = os.proc("git", "status").call()
gitStatus: os.CommandResult = CommandResult(
...

@ gitStatus.exitCode
res3: Int = 0

@ gitStatus.out.text()
res4: String = """On branch master
Your branch is up to date with 'origin/master'.
Changes to be committed:
...
