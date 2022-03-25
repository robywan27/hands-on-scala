package chapter_6

import scala.collection.mutable

class GenericTrie[T] {
  class Node(var value: Option[T], val children: mutable.Map[Char, Node] = mutable.Map())
  val root = new Node(None)

  def add(s: String, value: T) = {
    var current = root
    for (c <- s) current = current.children.getOrElseUpdate(c, new Node(None))
    current.value = Some(value)
  }
  def get(s: String): Option[T] = {
    var current = Option(root)
    for (c <- s if current.nonEmpty) current = current.get.children.get(c)
    current match {
      case Some(node) => node.value
      case _ => None
    }
  }
  def prefixesMatchingString(s: String): Map[String, T] = {
    def prefixesMatchingString0: Set[Int] = {
      var current = Option(root)
      val indexes = Set.newBuilder[Int]
      for ((c, i) <- s.zipWithIndex if current.nonEmpty) {
        if (current.get.value.nonEmpty) indexes += i
        current = current.get.children.get(c)
      }
      // in case s coincides with one word in the trie
      if (current.nonEmpty && current.get.value.nonEmpty) indexes += s.length
      indexes.result()
    }
    val prefixes = prefixesMatchingString0.map(s.substring(0, _))
    prefixes.map(s => s -> get(s).get).to(Map)
  }
  def stringsMatchingPrefix(s: String): Map[String, T] = {
    var current = Option(root)
    for (c <- s if current.nonEmpty) current = current.get.children.get(c)
    if (current.isEmpty) Map()
    else {
      val matches = Map.newBuilder[String, T]
      def recurse(current: Node, path: List[Char]): Unit = {
        if (current.value.nonEmpty) matches += ((s + path.reverse.mkString) -> current.value.get)
        for ((c, n) <- current.children) recurse(n, c :: path)
      }
      recurse(current.get, Nil)
      matches.result()
    }
  }
}

object GenericTrie {
  def main(args: Array[String]): Unit = {
    val t = new GenericTrie[Int]()
    t.add("mango", 1337)
    t.add("mandarin", 31337)
    t.add("map", 37)
    t.add("man", 7)

    println(t.get("mango"))
    println(t.get("mandarin"))
    println(t.get("mandolin"))

    assert(t.get("mango") == Some(1337))
    assert(t.prefixesMatchingString("mangosteen") == Map("man" -> 7, "mango" -> 1337))
    assert(t.stringsMatchingPrefix("mand") == Map("mandarin" -> 31337))
  }
}
