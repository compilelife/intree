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

If you are going to use it in java, don't forget:
```
dependencies {
	compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:1.1.4-2"
}
```

# detail
The PathTree is a tree represents pathes. We acess and create node by path. You can use it when structure file system path or uri path and so on.

In PathTree, there are two class named MakerWalker and NaiveWalker implements Walker. While MakerWalker will create non-exist node when walking, and NaiveWalker will just return null.

User-data are store in Node.data.

# intree
基于kotlin实现的树。仅一个文件。树通过路径访问。

# 使用
有多种方式
- 拷贝PathTree.kt到你的工程
- 或者依赖后clone的这个工程
- 或者clone后编译一个jar文件到目标工程使用
- 或者直接使用release的jar文件

使用PathTree只需两个步骤
1. 创建一棵树
```
val tree = PathTree<String>("root", ",")
```
2. 遍历
```
val node = tree.walk("/root/path/to/node", PathTree.MakerWalker())
```

如果你要在java中使用，需要添加kotlin运行库:
```
dependencies {
	compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:1.1.4-2"
}
```

# 其他
PathTree是一棵表示路径的树，我们通过路径访问和创建节点。你可以在诸如文件系统或uri路径的地方使用这个类来建模。

PathTree中有两个已经实现了Walker的类，分别是MakerWalker和NaiveWalker。其中MakerWalker会在遍历的时候创建不存在的节点，而NaiveWalker只会简单的返回null。

用户数据可以存储在Node.data中。