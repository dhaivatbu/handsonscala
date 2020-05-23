@ val helloRelPath = os.RelPath("../hello")

@ os.home / helloRelPath
res23: os.Path = /Users/hello

@ helloRelPath / os.RelPath("post")
res24: os.RelPath = ../hello/post
