@ evaluate(fastparse.parse("\"hello\"", Parser.expr(_)).get.value, Map.empty)
res79: Value = Str("hello")

@ val input = """{"hello": "world", "key": "value"}"""

@ evaluate(fastparse.parse(input, Parser.expr(_)).get.value, Map.empty)
res81: Value = Dict(Map("hello" -> Str("world"), "key" -> Str("value")))
