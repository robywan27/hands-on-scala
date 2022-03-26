import os.RelPath

os.pwd    // TODO: why is it returning "/" instead of the path to $PWD???
os.root
os.home

os.home.segments.toList
os.home.last

os.home / "Github"
os.home / os.up
// os.home / "." and os.home / ".." are not allowed

os.Path("/Users/rnapo")
os.RelPath("rnapo")

val pwd = os.Path("/Users/rnapo/Projects/hands-on-scala")
os.list(pwd)
os.walk(pwd / "notes")
os.stat(pwd / "build.sbt")

os.write(pwd / "temp.txt", "Temp file")
os.read(pwd / "temp.txt")
os.remove(pwd / "temp.txt")
os.makeDir(pwd / "temp")
os.write(pwd / "temp" / "file.txt", "Some text here")
os.read(pwd / "temp" / "file.txt")
os.list(pwd / "temp")
os.isDir(pwd / "temp")
os.isDir(pwd / "temp" / "file.txt")
os.list(pwd / "temp").map(os.remove)
os.remove(pwd / "temp")

