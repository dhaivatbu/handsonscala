
var logLevel = 1

def log(level: Int, msg: => String) = {
  if (level > logLevel) println(msg)
}

log(2, "Hello " + 123 + " World")

logLevel = 3
log(2, "Hello " + 123 + " World")

def measureTime(f: => Unit) = {
  val start = System.currentTimeMillis()
  f
  val end = System.currentTimeMillis()
  println("Evaluation took " + (end - start) + " milliseconds")
}

measureTime(new Array[String](10 * 1000 * 1000).hashCode())

measureTime { // methods taking a single arg can also be called with curly brackets
  new Array[String](100 * 1000 * 1000).hashCode()
}

def retry[T](max: Int)(f: => T): T = {
  var tries = 0
  var result: Option[T] = None
  while (result == None) {
    try { result = Some(f) }
    catch {case e: Throwable =>
      tries += 1
      if (tries > max) throw e
      else {
        println(s"failed, retry #$tries")
      }
    }
  }
  result.get
}

val httpbin = "https://httpbin.org"

retry(max = 50) {
  // Only succeeds with a 200 response
  // code 1/3 of the time
  requests.get(
    s"$httpbin/status/200,400,500"
  )
}
