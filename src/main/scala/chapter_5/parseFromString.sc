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

val args = Seq("12", "true", "4.5")
parseFromString[Int](args.head)
parseFromString[Boolean](args(1))
parseFromString[Double](args.last)


def parseFromConsole[T: StrParser] = {
  implicitly[StrParser[T]].parse(Console.in.readLine())
}

//val intArg = parseFromConsole[Int]


implicit def parseSeq[T: StrParser] = new StrParser[Seq[T]] {
  def parse(s: String) = s.split(",").toSeq.map(implicitly[StrParser[T]].parse)
}

parseFromString[Seq[Boolean]]("true,false,true")
parseFromString[Seq[Int]]("1,2,3")


implicit def parseTuple[S, T](implicit p1: StrParser[S], p2: StrParser[T]) = new StrParser[(S, T)] {
  override def parse(s: String): (S, T) = {
    val Array(left, right) = s.split('=')
    (p1.parse(left), p2.parse(right))
  }
}

parseFromString[(Int, Boolean)]("1=true")
parseFromString[(Boolean, Double)]("true=1.5")

parseFromString[Seq[(Int, Boolean)]]("1=true,2=false,3=true,4=false")
parseFromString[(Seq[Int], Seq[Boolean])]("1,2,3,4=true,false,true")