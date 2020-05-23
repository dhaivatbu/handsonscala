@ fastparse.parse("(hello  world)   ((goodbye seattle) world)", parser(_))
res69: Parsed[Phrase] = Success(
  Pair(
    Pair(Word("hello"), Word("world")),
    Pair(
      Pair(Word("goodbye"), Word("seattle")),
      Word("world")
    )
  ),
  42
)
