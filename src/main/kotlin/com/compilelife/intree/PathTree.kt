package com.compilelife.intree

import java.util.*

class Node<T>(val name: String){
    var parent : Node<T>? = null
    var data : T? = null
    private val children = HashMap<String, Node<T>>()

    fun getChildren() = children.values.toList()

    fun addChild(node: Node<T>): Node<T>{
        synchronized(children){
            node.parent = this
            children[node.name] = node
            return node
        }
    }

    fun delChild(node: Node<T>): Node<T>?{
        synchronized(children){
            node.parent = null
            return children.remove(node.name)
        }
    }

    fun child(name: String) = synchronized(children){
        children[name]
    }
}

/**
 * 路径索引树
 *
 * <p>可以通过路径查找树中的节点，或获取节点在树中的路径</p>
 */
class PathTree<T>(rootName: String, val delimiter: String) {
    val root: Node<T>
    init {
        val segs = splitPath(rootName)
        if (segs.isEmpty())
            throw IllegalArgumentException("rootName invalid: $rootName")
        else {
            if (segs.size >= 2)
                println("PathTree: rootName $rootName has too many segs, use first one")
            root = Node<T>(segs[0])
        }
    }

    interface Walker<T>{
        fun onError(): Node<T>?
        fun onHopeLess(cur:Node<T>, left:List<String>): Node<T>?
        fun onRoute(node: Node<T>):Boolean
    }

    fun splitPath(path:String): List<String>{
        return path.split(delimiter).filter { !it.isEmpty() }
    }

    /**
     * 获取节点所在路径
     * @param node 要查找的节点
     * @return 节点所在路径。 如果节点不在树中，则返回空字符串""
     */
    fun getPath(node: Node<*>): String {
        var cur: Node<*>? = node
        val sb = StringBuilder()
        var found = false

        while (cur != null) {
            sb.insert(0, cur.name)
            sb.insert(0, delimiter)

            if (cur === root)
                found = true

            cur = cur.parent
        }

        return if (found) sb.toString() else ""
    }

    fun walk(path: String, walker: Walker<T>): Node<T>?{
        return walk(splitPath(path), walker)
    }

    fun walk(segs: List<String>, walker:Walker<T>): Node<T>?{
        if (segs.isEmpty() || segs[0] != root.name)
            return walker.onError()

        var i = 0
        var cur = root

        do {
            if (!walker.onRoute(cur)) break
            if (++i >= segs.size) break

            cur = cur.child(segs[i]) ?: return walker.onHopeLess(cur, segs.subList(i, segs.size))
        }while (true)

        return cur
    }


    open class NaiveWalker<T> : Walker<T>{
        override fun onError(): Node<T>? = null

        override fun onHopeLess(cur: Node<T>, left: List<String>): Node<T>? = null

        override fun onRoute(node: Node<T>): Boolean = true
    }

    open class MakerWalker<T> : Walker<T>{
        override fun onError(): Node<T>? = null

        protected open fun newNode(name: String): Node<T> = Node(name)

        override fun onHopeLess(cur: Node<T>, left: List<String>): Node<T>? {
            var node = cur
            for (seg in left){
                node = node.addChild(newNode(seg))
                if (!onRoute(node))
                    break
            }

            return node
        }

        override fun onRoute(node: Node<T>): Boolean = true
    }
}