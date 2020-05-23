@ val smallExpr = BinOp(
    Variable("x"),
    "+",
    Literal(1)
  )

@ stringify(smallExpr)
res7: String = "(x + 1)"
