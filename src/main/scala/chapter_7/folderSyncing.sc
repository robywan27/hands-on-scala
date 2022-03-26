def sync(src: os.Path, dest: os.Path) = {
  for (srcSubPath <- os.walk(src)) {
    val subPath = srcSubPath.subRelativeTo(src)
    val destSubPath = dest / subPath
    (os.isDir(srcSubPath), os.isDir(destSubPath)) match {
      case (false, true) | (true, false) =>
        os.copy.over(srcSubPath, destSubPath, createFolders = true)
      case (false, false)
        if !os.exists(destSubPath)
        || !os.read.bytes(srcSubPath).sameElements(os.read.bytes(destSubPath)) =>
          os.copy.over(srcSubPath, destSubPath, createFolders = true)
      case _ => // nothing
    }
  }
  /*
  Exercise: remove file/folder in destination if not present in source
   */
  for (destSubPath <- os.walk(dest)) {
    val subPath = destSubPath.subRelativeTo(dest)
    val srcSubPath = src / subPath
    (os.isDir(srcSubPath), os.isDir(destSubPath)) match {
      case (false, true) if !os.exists(srcSubPath) =>
        // if folder then remove files inside the folder
        if (os.isDir(destSubPath)) os.list(destSubPath).map(os.remove)
        // remove file or in case it is a folder (if case) you still need to remove the folder
        os.remove(destSubPath)
      case _ => // all other cases have been handled before
    }
  }
}

val sampleBlogPath = os.Path("/Users/rnapo/Projects/hands-on-scala/sample-blog")
sync(sampleBlogPath / "post", sampleBlogPath / "post-copy")
os.walk(sampleBlogPath / "post-copy")
os.write(sampleBlogPath / "post" / "ABC.txt", "hellloooooooo")
sync(sampleBlogPath / "post", sampleBlogPath / "post-copy")
os.exists(sampleBlogPath / "post-copy" / "ABC.txt")
os.read(sampleBlogPath / "post-copy" / "ABC.txt")
os.write.append(sampleBlogPath / "post" / "ABC.txt", "something else here")
sync(sampleBlogPath / "post", sampleBlogPath / "post-copy")
os.read(sampleBlogPath / "post-copy" / "ABC.txt")

// Test exercise
os.makeDir(sampleBlogPath / "post-copy" / "temp")
os.write(sampleBlogPath / "post-copy" / "temp" / "file.txt", "Here is some text")
os.list(sampleBlogPath / "post-copy" / "temp")
sync(sampleBlogPath / "post", sampleBlogPath / "post-copy")
os.list(sampleBlogPath / "post-copy")
