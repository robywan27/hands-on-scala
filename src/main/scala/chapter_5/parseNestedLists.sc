// TODO
sealed trait StrParser[T] {
  def parse(s: String): T
}
object StrParser {
  implicit object ParseInt extends StrParser[Int] {
    def parse(s: String) = s.toInt
  }
  implicit object ParseBoolean extends StrParser[Boolean] {
    def parse(s: String) = s.toBoolean
  }
  implicit object ParseDouble extends StrParser[Double] {
    def parse(s: String) = s.toDouble
  }
}

def parseFromString[T: StrParser](s: String): T = {
  implicitly[StrParser[T]].parse(s)
}

implicit def parseSeq[T: StrParser] = new StrParser[Seq[T]] {
  def parse(s: String) = s.split(",").toSeq.map(implicitly[StrParser[T]].parse)
}

implicit def parseTuple[S, T](implicit p1: StrParser[S], p2: StrParser[T]) = new StrParser[(S, T)] {
  override def parse(s: String): (S, T) = {
    val Array(left, right) = s.split('=')
    (p1.parse(left), p2.parse(right))
  }
}


//implicit def parseNestedList[T](implicit p: StrParser[T]) = new StrParser[T] {
//  override def parse(s: String): T = {
//
//  }
//}


parseFromString[Seq[Boolean]]("[true,false,true]")
//parseFromString[Seq[(Seq[Int], Seq[Boolean])]](
//  "[[[1],[true]],[[2,3],[false,true]],[[4,5,6],[false,true,false]]]"
//)
//parseFromString[Seq[(Seq[Int], Seq[(Boolean, Double)])]](
//  "[[[1],[[true,0.5]]],[[2,3],[[false,1.5],[true,2.5]]]]"
//)