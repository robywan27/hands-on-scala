// TODO: fix array case
def flexibleFizzBuzz(outputControl: String => Unit): Unit = {
  for (i <- Range.inclusive(1, 100)) yield {
    outputControl(
      if (i % 3 == 0 && i % 5 == 0) "FizzBuzz"
      else if (i % 3 == 0) "Fizz"
      else if (i % 5 == 0) "Buzz"
      else s"$i"
    )
  }

}


flexibleFizzBuzz(_ => {})
flexibleFizzBuzz(println)

var i = 0
val output = new Array[String](100)
flexibleFizzBuzz { s =>
  output(i) = s
  i += 1
}