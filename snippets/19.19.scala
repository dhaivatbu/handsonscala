@ case class Phrase(isHello: Boolean, place: String)

@ def parser[_: P]: P[Phrase] =
    P( ("hello" | "goodbye").! ~ " ".rep(1) ~ ("world" | "seattle").! ~ End ).map{
      case ("hello", place) => Phrase(true, place)
      case ("goodbye", place) => Phrase(false, place)
    }

@ val Parsed.Success(result, index) = fastparse.parse("goodbye   seattle", parser(_))
result: Phrase = Phrase(false, "seattle")
index: Int = 17

@ result.isHello
res48: Boolean = false

@ result.place
res49: String = "seattle"
