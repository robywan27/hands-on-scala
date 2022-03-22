def retry[T](max: Int)(f: => T): T = {
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
       else println(s"Failed, try #$tries")
   }
  }
  result.get
}


val httpbin = "https://httpbin.org"
retry(max = 5) {
  requests.get(s"$httpbin/status/200,400,500")
}