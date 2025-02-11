package graphCollections

operator fun  <I,D> MutableSet<Graph.Edge<I>>.contains(vertex: Graph.Vertex<I, D>):Boolean{
   var result = false
   for (it in this) {
      if (it.adjacent == vertex.id) result = true
      if (result) break
   }
   return result
}
