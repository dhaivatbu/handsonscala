@ val input = """local f = function(a) a + "1";  f("123")"""

@ evaluate(fastparse.parse(input, Parser.expr(_)).get.value, Map.empty)
res118: Value = Str("1231")

@ val input = """local f = function(a, b) a + " " + b; f("hello", "world")"""

@ evaluate(fastparse.parse(input, Parser.expr(_)).get.value, Map.empty)
res119: Value = Str("hello world")
