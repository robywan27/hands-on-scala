// TODO: git is not detected when running as os process

val gitStatus = os.proc("git", "status").call()
gitStatus.exitCode
gitStatus.out.text()
gitStatus


val gitBranchLines = os.proc("git", "branch").call().out.lines()
//val otherBranches = gitBranchLines.filter(_.startsWith(" ")).map(_.drop(2))
val otherBranches = gitBranchLines.collect{ case s" $branchName" => branchName }
for (branch <- otherBranches) os.proc("git", "branch", "-D", branch).call()
gitBranchLines