@ val str = upickle.default.write(os.pwd)
str: String = "\"/Users/lihaoyi/Github/hands-on-scala-book\""

@ upickle.default.read[os.Path](str)
res3: os.Path = /Users/lihaoyi/Github/hands-on-scala-book

@ val str2 = upickle.default.write(Array(os.pwd, os.home, os.root))
str2: String = "[\"/Users/lihaoyi/Github/hands-on-scala-book\",\"/Users/lihaoyi\",\"/\"]"

@ upickle.default.read[Array[os.Path]](str2)
res5: Array[os.Path] = Array(/Users/lihaoyi/Github/hands-on-scala-book, /Users/lihaoyi, /)
