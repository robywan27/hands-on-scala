// TODO: is it correct?
def retry[T](max: Int, delay: Int)(f: => T): T = {
  val maxBackoff = delay + 64
  var tries = 0
  var result: Option[T] = None
  while (result.isEmpty) {
    try {
      result = Some(f)
    }
    catch {
      case e: Throwable =>
        tries += 1
        if (tries > max) throw e
        else {
          println(s"Failed, try #$tries")
          val expBackoff = Math.min(Math.pow(2, tries).toInt + delay, maxBackoff)
          Thread.sleep(expBackoff)
        }
    }
  }
  result.get
}

val httpbin = "https://httpbin.org"
retry(max = 50, delay = 100 /* milliseconds */) {
  requests.get(s"$httpbin/status/200,400,500")
}