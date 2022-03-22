import java.io.{BufferedReader, BufferedWriter, FileReader, FileWriter}

def withFileWriter(fileName: String)(fileWriter: BufferedWriter => Unit): Unit = {
  val bfw = new BufferedWriter(new FileWriter(fileName))
  fileWriter(bfw)
  bfw.close()
}

def withFileReader(fileName: String)(fileReader: BufferedReader => String): String = {
  val bfr = new BufferedReader(new FileReader(fileName))
  val text = fileReader(bfr)
  bfr.close()
  text
}



withFileWriter("/Users/rnapo/Projects/hands-on-scala/temp/File.txt") { writer =>
  writer.write("Hello\n"); writer.write("World")
}
val result = withFileReader("/Users/rnapo/Projects/hands-on-scala/temp/File.txt") { reader =>
  reader.readLine() + "\n" + reader.readLine()
}
assert(result == "Hello\nWorld")