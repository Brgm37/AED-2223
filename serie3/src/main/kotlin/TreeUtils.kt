
data class Node<E>(var item: E, var left:Node<E>?, var right:Node<E>?)

fun <E> contains(root: Node<E>?, min:E, max:E, cmp:(e1:E, e2:E)->Int):Boolean =
    if (root == null) false
    else if (cmp(root.item, min) < 0) contains(root.right, min, max, cmp)
    else if (cmp(root.item, max) > 0) contains(root.left, min, max, cmp)
    else true

fun <E> isBalanced(root: Node<E>?): Boolean {
    if (root == null || root.left == null && root.right == null) return true
    else{
        val left = if (root.left == null) 0 else treeHeight(root.left)+1
        val right = if (root.right == null) 0 else treeHeight(root.right)+1
        val test = isBalanced(root.left)
        val test1 = isBalanced(root.right)
        var mod = if (left-right < 0) (left-right)*(-1) else left-right
        return mod <= 1 && test && test1
    }
}

fun createBSTFromRange(start:Int,end:Int): Node<Int>? {
    if (start > end) return null
    if (start == end) return Node(start, null, null)
    val mid = (start+end)/2
    val node = Node(mid, null, null)
    node.left = createBSTFromRange(start, mid-1)
    node.right = createBSTFromRange(mid+1, end)
    return node
}
fun <E> treeHeight(root: Node<E>?): Int =
    if (root == null || root.left==null && root.right==null) 0
    else {
        val hl = treeHeight(root.left)
        val hr = treeHeight(root.right)
        if (hl>hr) hl+1 else hr+1
        }