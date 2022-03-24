import scala.collection.mutable

def bfsearch[T](start: T, graph: Map[T, Seq[T]]): Set[T] = {
  val seen = mutable.Set(start)
  val queue = mutable.ArrayDeque(start)
  while (queue.nonEmpty) {
    val current = queue.removeHead()
    for (next <- graph(current) if !seen.contains(next)) {
      seen.add(next)
      queue.append(next)
    }
  }
  seen.to(Set)
}


bfsearch(
  start = "c",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("a"),
    "c" -> Seq("b")
  )
)
bfsearch(
  start = "a",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("c", "d"),
    "c" -> Seq("d"),
    "d" -> Seq()
  )
)
bfsearch(
  start = "c",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("c", "d"),
    "c" -> Seq("d"),
    "d" -> Seq()
  )
)