import scala.annotation.tailrec

// TODO: fix implementation
class Msg(val id: Int, val parent: Option[Int], val txt: String)

def printMessages(messages: Array[Msg]): Unit = {
  for (m <- messages) {
    println(
      m.parent match {
        case Some(p) => s"\t${printMessages(messages)}#${m.id} ${m.txt}"
        case None => s"#${m.id} ${m.txt}"
      }
    )
  }

  val n = messages.length
  @tailrec
  def helper(messages: Array[Msg], i: Int): Unit = {
    if (i == n) ()
    else {
      val m = messages(i)
      println(
        m.parent match {
          case Some(_) => s"\t#${m.id} ${m.txt}"
          case None => s"#${m.id} ${m.txt}"
        }
      )
      helper(messages, i + 1)
    }
  }
  helper(messages, 0)
}


printMessages(Array(
  new Msg(0, None, "Hello"),
  new Msg(1, Some(0), "World"),
  new Msg(2, None, "I am Cow"),
  new Msg(3, Some(2), "Hear me moo"),
  new Msg(4, Some(2), "Here I stand"),
  new Msg(5, Some(2), "I am Cow"),
  new Msg(6, Some(5), "Here me moo, moo")
))