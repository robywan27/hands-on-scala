import scala.collection.mutable

val ab = Array.newBuilder[Int]
ab += 1
ab += 3
ab += 5
ab.result()

val ab2 = Array.newBuilder[Int]
ab2.result()

Array.fill(5)(3)
Array.fill(3)("hi")

Array.tabulate(5)(n => s"count $n")

Array(1, 2, 3, 4, 5).mkString(";")
Array(1, 2, 3, 4, 5).mkString("[", ",", "]")

val v = Vector(1, 2, 3, 4)
v.updated(2, 10)
v :+ 5
0 +: v

val s = Set(1, 3, 3, 2, 5, 1)
s + 2
s - 3

val toMap = Vector((1, "first"), (2, "second"), (3, "third")).to(Map)
toMap ++ Map(4 -> "fourth")
toMap + (5 -> "fifth")
toMap - 5

val l = List(1, 2, 3)
0 :: l

val adq = mutable.ArrayDeque(1, 2, 3, 4)
adq.removeHead()
adq.append(5)

val ms = mutable.Set(1, 4, 2, 3, 3)
ms.add(3)
ms.add(5)
ms
ms.remove(7)
ms.remove(3)
ms

val mm = mutable.Map("one" -> 1, "two" -> 2)
mm.remove("three")
mm.remove("one")
mm("three") = 3
mm
mm.getOrElseUpdate("one", 1)
mm.getOrElseUpdate("one", 1)
mm

adq.mapInPlace(_ + 1)
