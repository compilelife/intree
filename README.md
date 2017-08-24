# intree
One file tree-structure implement in kotlin.  Access tree by path.

# usage
There are many ways to get started.
- Copy PathTree.kt to your project. 
- Or clone project to add depency to. 
- Or clone project and build a jar to add to target project.
- Or directly use release jar.

Only two steps to use PathTree.
1. ceate a tree
```
val tree = PathTree<String>("root", ",")
```
2. walk the tree
```
val node = tree.walk("/root/path/to/node", PathTree.MakerWalker())
```

# detail
The PathTree is a tree represents pathes. We acess and create node by path. You can use it when structure file system path or uri path and so on.

In PathTree, there are two class named MakerWalker and NaiveWalker implements Walker. While MakerWalker will create non-exist node when walking, and NaiveWalker will just return null.

User-data are store in Node.data.
