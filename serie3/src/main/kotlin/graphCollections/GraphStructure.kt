package graphCollections
class GraphStructure<I, D>: Graph<I, D> {
    private class VertexStructure<I, D>(i: I, d: D) : Graph.Vertex<I, D>{
        override var data: D = d
        override val id: I = i
        private val listOfAdjacent = mutableSetOf<Graph.Edge<I>>()
        override fun getAdjacencies(): MutableSet<Graph.Edge<I>> = listOfAdjacent

        override fun setData(newData: D): D {
            data = newData
            return newData
        }
    }
    private class EdgeStructure<I>(i: I, adj: I) : Graph.Edge<I>{
        override var adjacent: I = adj
        override var id: I = i
    }
    private val graph = mutableMapOf<I, Graph.Vertex<I, D>>()
    override var size: Int = 0

    override fun getEdge(id: I, idAdj: I): Graph.Edge<I>? =
        graph[id]?.getAdjacencies()?.find { it.adjacent == idAdj }
    override fun getVertex(id: I): Graph.Vertex<I, D>? = graph[id]
    override fun addEdge(id: I, idAdj: I): I? =
        if (graph[id] == null) null
        else {
            graph[id]?.getAdjacencies()?.add(EdgeStructure(id, idAdj))
            idAdj
        }
    override fun addVertex(id: I, d: D): D? =
        if (graph[id] != null)
            null
        else {
            size++
            val newVertex = VertexStructure(id, d)
            graph[id] = newVertex
            d
        }

    override fun iterator(): Iterator<Graph.Vertex<I, D>> = VertexIterator()
    private inner class VertexIterator: Iterator<Graph.Vertex<I, D>>{
        var currVertex: Graph.Vertex<I, D>? = null
        val it = graph.iterator()
        override fun hasNext(): Boolean {
            if (currVertex != null)
                 return true
            return if (it.hasNext()) {
                currVertex = it.next().value
                true
            }
            else
                false
        }
        override fun next(): Graph.Vertex<I, D> {
            if (!hasNext()) throw NoSuchElementException()
            val aux = currVertex
            currVertex = null
            return aux as Graph.Vertex<I, D>
        }
    }
}