@ val input = """local greeting = "Hello "; greeting + greeting"""

@ evaluate(fastparse.parse(input, Parser.expr(_)).get.value, Map.empty)
res94: Value = Str("Hello Hello ")

@ val input = """local x = "Hello "; local y = "world"; x + y"""

@ evaluate(fastparse.parse(input, Parser.expr(_)).get.value, Map.empty)
res96: Value = Str("Hello world")
