import scala.collection.mutable

def searchPaths[T](start: T, graph: Map[T, Seq[T]]): Map[T, List[T]] = {
  val seen = mutable.Map(start -> List(start))
  val queue = mutable.ArrayDeque(start -> List(start))
  while (queue.nonEmpty) {
    val (current, path) = queue.removeHead()
    for (next <- graph(current) if !seen.contains(next)) {
      val newPath = next :: path
      seen(next) = newPath
      queue.append((next, newPath))
    }
  }
  seen.to(Map)
}

def shortestPath[T](start: T, dest: T, graph: Map[T, Seq[T]]): Seq[T] = {
  val shortestReversedPaths = searchPaths(start, graph)
  shortestReversedPaths(dest).reverse
}


searchPaths(
  start = "a",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("c", "d"),
    "c" -> Seq("d"),
    "d" -> Seq()
  )
)
shortestPath(
  start = "a",
  dest = "d",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("c", "d"),
    "c" -> Seq("d"),
    "d" -> Seq()
  )
)
shortestPath(
  start = "a",
  dest = "c",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("c", "d"),
    "c" -> Seq("d"),
    "d" -> Seq()
  )
)