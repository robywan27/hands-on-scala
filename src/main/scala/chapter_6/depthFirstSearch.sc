import scala.collection.mutable

def dfsearch[T](start: T, graph: Map[T, Seq[T]]): Set[T] = {
  val seen = mutable.Set(start)
  val stack = mutable.ArrayDeque(start)
  while (stack.nonEmpty) {
    val current = stack.removeLast()
    for (next <- graph(current) if !seen.contains(next)) {
      seen.add(next)
      stack.append(next)
    }
  }
  seen.to(Set)
}

def shortestPath[T](start: T, dest: T, graph: Map[T, Seq[T]]): Seq[T] = {
  def searchPaths: Map[T, List[T]] = {
    val seen = mutable.Map(start -> List(start))
    val stack = mutable.ArrayDeque(start -> List(start))
    while (stack.nonEmpty) {
      val (current, path) = stack.removeLast()
      for (next <- graph(current) if !seen.contains(next)) {
        seen(next) = next :: path
        stack.append((next, next :: path))
      }
    }
    seen.to(Map)
  }
  val reversedShortestPaths = searchPaths
  reversedShortestPaths(dest).reverse
}


dfsearch(
  start = "c",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("a"),
    "c" -> Seq("b")
  )
)
dfsearch(
  start = "a",
  graph = Map(
    "a" -> Seq("b", "c"),
    "b" -> Seq("c", "d"),
    "c" -> Seq("d"),
    "d" -> Seq()
  )
)
dfsearch(
  start = "c",
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