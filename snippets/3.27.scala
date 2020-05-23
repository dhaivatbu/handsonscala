@ def nameLength(name: Option[String]) = {
    name.map(_.length).getOrElse(-1)
  }

@ nameLength(Some("Haoyi"))
res6: Int = 5

@ nameLength(None)
res7: Int = -1
