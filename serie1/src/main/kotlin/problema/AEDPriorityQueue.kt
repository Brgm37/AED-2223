package problema
import kotlin.time.*
data class AEDPriorityQueue(
    val heap: MutableList<File?>,
    var size: Int,
    val cmp: (a:File?, b:File?) -> Int
    )
fun AEDPriorityQueue.init(list: List<File>) = list.forEach(::offer)
fun cmp(a: File?, b: File?) =
    when{
        a != null && b != null -> cmp(a, b)
        a == null && b != null -> -1
        a != null && b == null -> 1
        else -> 0
    }
fun AEDPriorityQueue.isNotEmpty() = size != 0
fun AEDPriorityQueue.offer(element: File): Boolean{
    if (size == heap.size) return false
    heap[size] = element
    decreaseKey(size++)
    return true
}
private fun AEDPriorityQueue.decreaseKey(i: Int){
    var a = i
    while (a > 0 && cmp(heap[a], heap[parent(a)]) < 0){
        exchange(a, parent(a))
        a = parent(a)
    }
}
fun AEDPriorityQueue.peak() = if (size == 0) null else heap.first()
fun AEDPriorityQueue.poll(): File?{
    if (size == 0) return null
    val element = heap.first()
    heap[0] = heap[--size]
    minHeapify(0)
    return element
}
private fun AEDPriorityQueue.minHeapify(i: Int){
    var smallest = i
    val l = left(i)
    val r = right(i)
    if (l < size && cmp(heap[l], heap[i]) < 0)smallest = l
    if (r < size && cmp(heap[r], heap[smallest]) < 0) smallest = r
    if (smallest != i){
        exchange(i, smallest)
        minHeapify(smallest)
    }
}
private fun AEDPriorityQueue.exchange(a: Int, b: Int){
    val holder = heap[a]
    heap[a] = heap[b]
    heap[b] = holder
}
private fun parent(i:Int) = (i-1)/2
private fun left(i: Int) = 2*i+1
private fun right(i: Int) = 2*i+2