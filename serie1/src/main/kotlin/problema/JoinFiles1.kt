package problema
import java.io.BufferedReader
import java.util.PriorityQueue
import kotlin.time.*
data class File(
    val idx: Int,
    val string: String?
)
fun PriorityQueue<File>.init(file: Array<BufferedReader>) =
    file.forEachIndexed { index, bufferedReader ->
        offer(File(index, bufferedReader.readLine()))
    }

fun String?.compareToNullable(b: String?): Int =
    when{
        this == null && b != null -> -1
        this != null && b == null -> 1
        this != null && b != null -> compareTo(b)
        else -> 0
    }
fun cmp (a: File, b: File):Int = a.string.compareToNullable(b.string)
@ExperimentalTime
fun main(args: Array<String>){
    val n = args.lastIndex /*number of files read by the program(without the output file)*/
    val output = createWriter(args.first()) /*output file generate by the program*/
    val fileReader = Array(n){ createReader((args[it+1])) } /*an Array with all the files read by the program*/
    val pq = PriorityQueue(::cmp)
    pq.init(fileReader) /*initiate the priority queue with the first lines of the fileReader elements*/
    var validation: String? = null
    val elapsed2: Duration = measureTime {
        while (pq.isNotEmpty()) {
            val poll = pq.poll() /*the smallest element of the priority queue*/
            val newLine = File(poll.idx, fileReader[poll.idx].readLine())
            if (newLine.string != null) pq.offer(newLine)
            if (validation == null || validation != poll.string) {
                output.println(poll.string)
                validation = poll.string
            }
        }
        output.close()
    }
    println(elapsed2)
}