package serie2
import java.util.PriorityQueue

class StreamMedian {
    private val minHeap = PriorityQueue{a: Int, b: Int -> when{
            a < b -> 1
            a > b -> -1
            else -> 0
        } }
    private val maxHeap = PriorityQueue{a: Int, b: Int -> when{
            a < b -> -1
            a > b -> 1
            else -> 0
        } }
    private var size: Int = 0
    fun updateSet(v: Int){
        if (size == 0) {
            minHeap.offer(v)
            size++
        }else if (size%2 != 0) {
            if (v > minHeap.peek())
                maxHeap.offer(v)
            else {
                val exchange = minHeap.poll()
                minHeap.offer(v)
                maxHeap.offer(exchange)
            }
            size++
        }else{
            if (v > minHeap.peek()) {
                val exchange = maxHeap.poll()
                maxHeap.offer(v)
                minHeap.offer(exchange)
            }else{
                minHeap.offer(v)
            }
            size++
        } }
    fun getMedian() =
        if(size%2 == 0)
            (minHeap.peek() + maxHeap.peek())/2
        else
            minHeap.peek() ?: 0
}