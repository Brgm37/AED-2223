package problema

import kotlin.time.*
@ExperimentalTime

fun main(args: Array<String>){
    val n = args.lastIndex /*number of files read by the program(without the output file)*/
    val output = createWriter(args.first()) /*output file generate by the program*/
    val fileReader = List(n){ createReader((args[it+1])) } /*List whit al the files read by the program*/
    /*a list with all the first line elements of each file*/
    val init = fileReader.mapIndexed { index, bufferedReader -> File(index, bufferedReader.readLine()) }
    val pq = AEDPriorityQueue(MutableList(n){null}, 0, ::cmp)
    pq.init(init)
    var validation: String? = null
    val elapsed1: Duration = measureTime {
        while (pq.isNotEmpty()) {
            val poll = pq.poll() ?: break /*the smallest element of the priority queue*/
            val newLine = File(poll.idx, fileReader[poll.idx].readLine())
            if (newLine.string != null) pq.offer(newLine)
            if (validation == null || validation != poll.string) {
                output.println(poll.string)
                validation = poll.string
            }
        }
        output.close()
    }
    println(elapsed1)
}
