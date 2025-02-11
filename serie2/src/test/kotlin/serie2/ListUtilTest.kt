package serie2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.util.*
import kotlin.collections.ArrayList

/*
* For circular double linked lists with sentinel
*/
fun <E> emptyListWithSentinel(): Node<E> = Node()

fun <E> isEmptyListWithSentinel(list: Node<E>) =
    list.next == list && list.previous == list

fun <E> makeListWithSentinel(array: ArrayList<E>): Node<E> {
    val list: Node<E> = emptyListWithSentinel()
    for (v in array) {
        val new = Node(v, list.previous, list)
        list.previous.let { lp ->
            lp.next = new
        }
        list.previous = new
    }
    return list
}

fun <E> assertListEqualsWithSentinel(expected: Node<E>, result: Node<E>, cmp: Comparator<E>) {
    var listExpected = expected.next
    var listResult = result.next
    while (listExpected != expected && listResult != result) {
        assertEquals(0, cmp.compare(listExpected.value, listResult.value))
        listExpected = listExpected.next
        listResult = listResult.next
    }
    assertTrue(listExpected == expected)
    assertTrue(listResult == result)
}


fun getListWithSentinel(begin: Int, end: Int, step: Int): Node<Int> {
    val array = ArrayList<Int>()
    var i = begin
    while (i <= end) {
        array.add(i)
        i += step
    }
    return makeListWithSentinel(array)
}



fun <E> printListWithSentinel(list: Node<E>) {
    var curr = list.next
    while (curr != list) {
        print("${curr.value} ")
        curr = curr.next
    }
    println()
}


fun <E> newNode(v: E, p: Node<E>, n: Node<E>): Node<E> {
    val result = Node(v,p,n)
    result.previous = p
    result.next = n
    return result
}

fun <E> makeList(vararg array: E): Node<E> {
    val list: Node<E> = emptyListWithSentinel()
    for (v in array) {
        val new = newNode(v, list.previous, list)
        list.previous?.let { lp ->
            lp.next = new
        }
        list.previous = new
    }
    return list
}


