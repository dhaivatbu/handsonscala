@ implicit val foo: Foo = new Foo(1)
foo: Foo = ammonite.$sess.cmd1$Foo@451882b2

@ bar // `foo` is resolved implicitly
res5: Int = 11

@ bar(foo) // passing in `foo` explicitly
res6: Int = 11
