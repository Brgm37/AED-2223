import graphCollections.GraphStructure
import graphCollections.contains
import java.io.BufferedReader
import java.util.PriorityQueue
import kotlin.time.*
@ExperimentalTime
fun main(args: Array<String>) {
    val myReader = createReader(args.first())
    val listCounting = createWriter("listCounting.txt")
    val graph = readFile(myReader, " ",0, false)
    var triangle: PriorityQueue<Pair<Int, Int>>
    val elapsed:Duration = measureTime {
        triangle = numberOfTriangle(graph, false)
    }
    println("Time == $elapsed")
    val kTriangle = PriorityQueue(::cmp)
    while (triangle.isNotEmpty()) {
        val vertex = triangle.poll()
        kTriangle.offer(vertex)
        listCounting.println("${vertex.first} -> ${vertex.second}")
    }
    listCounting.close()
    while (true){
        print( "\nIntroduza um número positivo k ou 0 se quiser terminar a aplicação: ")
        val k = readln().toInt()
        if (k <= 0)
            break
        val kCounting = createWriter("kCounting.txt")
        val save = mutableListOf<Pair<Int, Int>>()
        repeat(k){
            val it = kTriangle.poll()
            save.add(it)
            kCounting.println("${it.first} -> ${it.second}")
        }
        kCounting.close()
        save.forEach { kTriangle.offer(it) }
        save.clear()
    }
}
fun cmp(a: Pair<Int, Int>, b: Pair<Int, Int>): Int =
    when{
        a.second > b.second -> -1
        a.second < b.second -> 1
        else -> 0
    }
fun readFile(
    reader: BufferedReader,
    separator: String,
    steps: Int,
    oriented: Boolean
): GraphStructure<Int, Int>{
    repeat(steps){
        reader.readLine()
    }
    val graph = GraphStructure<Int, Int>()
    var line = reader.readLine()
    while (line != null){
        val vertex = line.split(separator).map { it.toInt() }
        vertex.forEach { graph.addVertex(it, it) }
        graph.addEdge(vertex.first(), vertex[1])
        if (!oriented)
            graph.addEdge(vertex[1], vertex.first())
        line = reader.readLine()
    }
    return graph
}
fun numberOfTriangle(graph: GraphStructure<Int, Int>, oriented: Boolean): PriorityQueue<Pair<Int, Int>>{
    val finalList = PriorityQueue(::cmp1)
    for (it in graph.iterator()){
        var numberOfTriangles = 0
        for (adj in it.getAdjacencies()){
            val adjVertex = graph.getVertex(adj.adjacent)
            if (adjVertex != null) {
                for (thirdId in adjVertex.getAdjacencies()) {
                    val thirdVertex = graph.getVertex(thirdId.adjacent)
                    if (thirdVertex != null) {
                        if(thirdVertex.getAdjacencies().contains(it))
                            numberOfTriangles++
                    }
                }
            }
        }
        if (!oriented) numberOfTriangles /= 2
        finalList.offer(Pair(it.data, numberOfTriangles))
    }
    return finalList
}
fun cmp1(a: Pair<Int, Int>, b: Pair<Int, Int>): Int =
    when{
        a.first > b.first -> 1
        a.first < b.first -> -1
        else -> 0
    }
