package chapter_6

import scala.collection.mutable

class Trie {
  class Node(var hasValue: Boolean, val children: mutable.Map[Char, Node] = mutable.Map())
  val root = new Node(false)

  def add(s: String) = {
    var current = root
    for (c <- s)  current = current.children.getOrElseUpdate(c, new Node(false))
    current.hasValue = true
  }

  def contains(s: String): Boolean = {
    var current = Option(root)
    for (c <- s if current.nonEmpty)  current = current.get.children.get(c)
    current.exists(_.hasValue)
  }

  def prefixesMatchingString(s: String): Set[String] = {
    def prefixesMatchingString0: Set[Int] = {
      var current = Option(root)
      val indexes = Set.newBuilder[Int]
      for ((c, i) <- s.zipWithIndex if current.nonEmpty) {
        if (current.get.hasValue) indexes += i
        current = current.get.children.get(c)
      }
      // in case s coincides with one word in the trie
      if (current.exists(_.hasValue)) indexes += s.length
      indexes.result()
    }
    prefixesMatchingString0.map(s.substring(0, _))
  }

  def stringsMatchingPrefix(s: String): Set[String] = {
    var current = Option(root)
    for (c <- s if current.nonEmpty) current = current.get.children.get(c)
    if (current.isEmpty) Set()
    else {
      val matches = Set.newBuilder[String]
      def recurse(current: Node, path: List[Char]): Unit = {
        if (current.hasValue) matches += (s + path.reverse.mkString)
        for ((c, n) <- current.children) recurse(n, c :: path)
      }
      recurse(current.get, Nil)
      matches.result()
    }
  }

}
