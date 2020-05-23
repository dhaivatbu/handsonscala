# Example 18.2 - Pipelined
Pipelined real-time file synchronizer, allowing RPCs and hashing to take place
in parallel

```bash
./mill -i sync.test
```

## Upstream Example: [18.1 - Simple](https://github.com/handsonscala/handsonscala/tree/master/examples/18.1%20-%20Simple):
Diff:
```diff
diff --git a/18.1 - Simple/sync/src/Sync.scala b/18.2 - Pipelined/sync/src/Sync.scala
index db91ef0..897adce 100644
--- a/18.1 - Simple/sync/src/Sync.scala	
+++ b/18.2 - Pipelined/sync/src/Sync.scala	
@@ -1,36 +1,49 @@
 package sync
 object Sync {
   def main(args: Array[String]): Unit = {
-    val (src, dest) = (os.Path(args(0), os.pwd), os.Path(args(1), os.pwd))
+    val src = os.Path(args(0), os.pwd)
+    val dest = os.Path(args(1), os.pwd)
+    println(s"Sync.main $src $dest")
     val agentExecutable = os.temp(os.read.bytes(os.resource / "agent.jar"))
     os.perms.set(agentExecutable, "rwx------")
     val agent = os.proc(agentExecutable).spawn(cwd = dest)
     sealed trait Msg
     case class ChangedPath(value: os.SubPath) extends Msg
-    case class AgentResponse(value: Rpc.StatInfo) extends Msg
+    case class HashStatInfo(localHash: Option[Int], value: Rpc.StatInfo) extends Msg
     import castor.Context.Simple.global
     object SyncActor extends castor.SimpleActor[Msg]{
       def run(msg: Msg): Unit = {
         println("SyncActor handling: " + msg)
         msg match {
           case ChangedPath(value) => Shared.send(agent.stdin.data, Rpc.StatPath(value))
-          case AgentResponse(Rpc.StatInfo(p, remoteHash)) =>
-            val localHash = Shared.hashPath(src / p)
+          case HashStatInfo(localHash, Rpc.StatInfo(p, remoteHash)) =>
             if (localHash != remoteHash && localHash.isDefined) {
               Shared.send(agent.stdin.data, Rpc.WriteOver(os.read.bytes(src / p), p))
             }
         }
       }
     }
+    object HashActor extends castor.SimpleActor[Rpc.StatInfo]{
+      def run(msg: Rpc.StatInfo): Unit = {
+        println("HashActor handling: " + msg)
+        val localHash = Shared.hashPath(src / msg.p)
+        SyncActor.send(HashStatInfo(localHash, msg))
+      }
+    }
+    println(s"Sync.main agentReader")
     val agentReader = new Thread(() => {
       while (agent.isAlive()) {
-        SyncActor.send(AgentResponse(Shared.receive[Rpc.StatInfo](agent.stdout.data)))
+        HashActor.send(Shared.receive[Rpc.StatInfo](agent.stdout.data))
       }
     })
     agentReader.start()
+    println(s"Sync.main watcher")
     val watcher = os.watch.watch(
       Seq(src),
-      onEvent = _.foreach(p => SyncActor.send(ChangedPath(p.subRelativeTo(src))))
+      onEvent = _.foreach{p =>
+        println(s"Sync.main watcher onEvent p " + p)
+        SyncActor.send(ChangedPath(p.subRelativeTo(src)))
+      }
     )
     Thread.sleep(Long.MaxValue)
   }
```
## Downstream Examples

- [18.3 - InitialFiles](https://github.com/handsonscala/handsonscala/tree/master/examples/18.3%20-%20InitialFiles)
- [18.4 - ForkJoinHashing](https://github.com/handsonscala/handsonscala/tree/master/examples/18.4%20-%20ForkJoinHashing)
- [18.5 - Deletes](https://github.com/handsonscala/handsonscala/tree/master/examples/18.5%20-%20Deletes)
- [18.6 - VirtualFileSystem](https://github.com/handsonscala/handsonscala/tree/master/examples/18.6%20-%20VirtualFileSystem)