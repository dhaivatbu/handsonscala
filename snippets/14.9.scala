-   def hello() = {
-     "Hello World!"
-   }
+   val bootstrap = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.css"
+
+   def hello() = doctype("html")(
+     html(
+       head(link(rel := "stylesheet", href := bootstrap)),
+       body(
+         div(cls := "container")(
+           h1("Hello!"),
+           p("World")
+         )
+       )
+     )
+   )