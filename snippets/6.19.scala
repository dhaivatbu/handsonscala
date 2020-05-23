@ val t = new Trie(); t.add("mango"); t.add("mandarin"); t.add("map"); t.add("man")

@ t.stringsMatchingPrefix("man")
res121: Set[String] = Set("man", "mandarin", "mango")

@ t.stringsMatchingPrefix("ma")
res122: Set[String] = Set("map", "man", "mandarin", "mango")

@ t.stringsMatchingPrefix("map")
res123: Set[String] = Set("map")

@ t.stringsMatchingPrefix("mand")
res124: Set[String] = Set("mandarin")

@ t.stringsMatchingPrefix("mando")
res125: Set[String] = Set()
