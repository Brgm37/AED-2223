package serie2

fun <E> removeAfterIntersectionPoint(list1: Node<E>, list2: Node<E>, cmp: Comparator<E>) {
   var list1Prev = list1.previous
    var list2Prev = list2.previous
    while (list1Prev != list1 && list2Prev != list2){
        if (cmp.compare(list1Prev.value, list2Prev.value) == 0){
            list1Prev = list1Prev.previous
            list2Prev = list2Prev.previous
        } else {
            break
        }
    }
    list1Prev.next = list1
    list1.previous = list1Prev
}

fun <E> xor(list1: Node<E>, list2: Node<E>, cmp: Comparator<E>): Node<E> {
    val final = Node<E>()
    var list1Idx = list1.next
    var list2Idx = list2.next
    while (list1Idx != list1 || list2Idx != list2){
        if (list1Idx != list1 && list2Idx != list2)
            when(cmp.compare(list1Idx.value, list2Idx.value)){
                -1 -> {
                    list1Idx = list1Idx.interaction(final, list1, cmp)
                }
                1 -> {
                    list2Idx = list2Idx.interaction(final, list2, cmp)
                }
                else -> {
                    list1Idx = list1Idx.next
                    list2Idx = list2Idx.next
                }
            }
        else if (list1Idx != list1){
            list1Idx = list1Idx.interaction(final, list1, cmp)
        }
        else{
            list2Idx = list2Idx.interaction(final, list2, cmp)
        }
    }
    return final
}

class Node<E> {
    val value: E
    var previous: Node<E>
    var next: Node<E>

    constructor() {
        value = Any() as E
        previous = this
        next = this
    }

    constructor(data: E, p: Node<E>, n: Node<E>) {
        value = data
        previous = p
        next = n
    }
    fun add(new: Node<E>){
        new.previous = previous
        previous.next = new
        new.next = this
        previous = new
    }
    fun remove(elm: Node<E>, cmp: Comparator<E>){
        var x = next
        while (x != this){
            if (cmp.compare(x.value, elm.value) == 0){
                x.next.previous = x.previous
                x.previous.next = x.next
                break
            } else
                x = x.next
        }
    }
    fun interaction(final: Node<E>, list: Node<E>, cmp: Comparator<E>): Node<E> {
        val mem = next
        list.remove(this, cmp)
        final.add(this)
        return mem
    }
}

