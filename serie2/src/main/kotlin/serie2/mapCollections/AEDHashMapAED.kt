package serie2.mapCollections

class AEDHashMapAED<K, V> : AEDMutableMap<K, V> {
    class AEDHashNode<K, V>(k: K, v: V) : AEDMutableMap.MutableEntry<K, V>{
        override val key: K = k
        override var value: V = v
        var next:AEDHashNode<K, V>? = null
        var previous: AEDHashNode<K, V>? = null
        override fun setValue(newValue: V): V {
            val old = value
            value = newValue
            return old
        }
    }

    private var table: Array<AEDHashNode<K, V>?> = arrayOfNulls(100)
    override var size: Int=0
        private set
    private var dim: Int = table.size
    override fun put(key: K, value: V): V? {
        if (size.toDouble()/dim >= 0.75)
            resize()
        val idx = hash(key)
        return if (containsKey(key)){
            val old = table[idx]
            if (old != null)
                table[idx] = add(old, AEDHashNode(key, value))
            old?.value
        }else{
            table[idx] = AEDHashNode(key, value)
            size++
            null
        }
    }
    override operator fun get(key: K): V? {
        var node = table[hash(key)]
        while (node != null){
            if(key == node.key)
                return node.value
            node = node.next
        }
        return null
    }
    override fun remove(key: K): V? {
        val idx = hash(key)
        var node = table[idx]
        while (node != null){
            if(key == node.key){
                if (node.next != null)
                    node.next = node.previous?.next
                if (node.previous != null)
                    node.previous.let { nodePrev ->
                        node.let { nodePrev?.next = it?.next }
                    }
                else
                    table[idx] = node.next
                return node.value
            }
            node = node.next
        }
        return null
    }
    override fun iterator(): Iterator<AEDMutableMap.MutableEntry<K, V>> =
        AEDHashMapAEDIterator()
    override fun containsKey(k:K):Boolean = table[hash(k)] != null
    private fun hash(key: K): Int{
        var idx = key.hashCode() % dim
        if (idx < 0) idx+=dim
        return idx
    }
    private inner class AEDHashMapAEDIterator: Iterator<AEDMutableMap.MutableEntry<K, V>>{
        var idx = -1
        var node: AEDHashNode<K, V>? = null
        var list: AEDHashNode<K, V>? = null
        override fun next(): AEDMutableMap.MutableEntry<K, V> {
            if(!hasNext())
                throw NotImplementedError()
            val result = node
            node = null
            return result as AEDMutableMap.MutableEntry<K, V>
        }
        override fun hasNext(): Boolean {
            if (node != null) return true
            while (idx < dim){
                if (list == null){
                    idx++
                    if(idx < dim) list = table[idx]
                }else{
                    node = list
                    list?.let { l -> list = l.next }
                    return true
                }
            }
            return false
        }
    }
    private fun resize(){
        dim *= 2
        val newTable = arrayOfNulls<AEDHashNode<K, V>>(dim)
        for (i in table.indices){
            var curr = table[i]
            while (curr != null){
                table[i] = table[i]?.next
                val idx = hash(curr.key)
                curr.next = newTable[idx]
                newTable[idx]?.let { nT -> nT.previous = curr }
                newTable[idx] = curr
                curr.previous = null
                curr = table[i]
            }
        }
        table = newTable
    }
    private fun add(head: AEDHashNode<K, V>, new: AEDHashNode<K, V>): AEDHashNode<K, V>{
        var x: AEDHashNode<K, V>? = head
        while (x?.next != null){
            if(x.key == new.key){
                return if (x.previous != null) {
                    new.next = x
                    x.previous?.let { it.next = new }
                    new.previous = x.previous
                    x.previous = new
                    head
                } else {
                    head.previous = new
                    new.next = head
                    new
                }
            }
            x = x.next
        }
        x?.let { it.next = new }
        new.previous = x
        return head
    }
}
