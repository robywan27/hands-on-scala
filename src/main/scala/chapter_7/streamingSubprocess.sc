// TODO: Fix tee

val sub = os.proc("python3", "-u", "-c", "while True: print(eval(input()))").spawn()
//sub.stdin.writeLine("1 + 2 + 4")
//sub.stdin.flush()
//sub.stdout.readLine()
sub.isAlive()
sub.destroy()
sub.isAlive()

val gitLog = os.proc("git", "log").spawn()
val grepAuthor = os.proc("grep", "Author: ").spawn(stdin = gitLog.stdout)
val output = grepAuthor.stdout.lines().distinct

val download = os
  .proc("curl", "https://api.github.com/repos/lihaoyi/mill/releases")
  .spawn()
val base64 = os.proc("base64").spawn(stdin = download.stdout)
val gzip = os.proc("gzip").spawn(stdin = base64.stdout)
val upload = os
  .proc("curl", "-X", "PUT", "-d", "@-", "https://httpbin.org/anything")
  .spawn(stdin = gzip.stdout)
/*
Exercise: add tee to write compressed data to local file
 */
val tee = os.proc("tee", "local.zip").spawn(stdin = gzip.stdout)
///////
val contentLength = upload.stdout.lines().filter(_.contains("Content-Length"))