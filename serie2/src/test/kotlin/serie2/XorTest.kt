package serie2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class XorTest {

    private val CMP_NATURAL_ORDER = Comparator{ i1:Int, i2:Int -> i1.compareTo(i2)}



    @Test
    fun xor_empty_lists() {
        val elements = arrayListOf(0, 1)
        var list1: Node<Int> = emptyListWithSentinel()
        var list2: Node<Int> = emptyListWithSentinel()
        assertTrue(isEmptyListWithSentinel(xor(list1, list2, CMP_NATURAL_ORDER)))
        list1 = getListWithSentinel(0, 1, 1)
        list2 = getListWithSentinel(0, 1, 1)
        assertTrue(isEmptyListWithSentinel(xor(list1, list2, CMP_NATURAL_ORDER)))
        var curr1 = list1.next
        var curr2 = list2.next
        for (i in elements.indices) {
            assertTrue(elements[i] == curr1.value)
            assertTrue(elements[i] == curr2.value)
            curr1 = curr1.next
            curr2 = curr2.next
        }
    }

    @Test
    fun xor_singleton_lists() {
        var list1: Node<Int> = getListWithSentinel(0, 1, 1)
        var list2: Node<Int> = getListWithSentinel(1, 1, 1)
        val res = xor(list1, list2, CMP_NATURAL_ORDER)
        assertEquals(0, res.next.value)
        assertTrue(list1.next.value == 1 && list1.previous.value == 1)
        assertTrue(list2.next.value == 1 && list2.previous.value == 1)
        list1 = getListWithSentinel(1, 1, 1)
        list2 = getListWithSentinel(1, 1, 1)
        assertTrue(isEmptyListWithSentinel(xor(list1, list2, CMP_NATURAL_ORDER)))
        assertTrue(list1.next.value == 1 && list1.previous.value == 1)
        assertTrue(list2.next.value == 1 && list2.previous.value == 1)
    }

    @Test
    fun xor_different_lists() {
        val elements = arrayListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val list1 = getListWithSentinel(0, 10, 2)
        val list2 = getListWithSentinel(1, 10, 2)
        val res = (xor(list1, list2, CMP_NATURAL_ORDER))
        var curr = res.next
        for (i in elements.indices) {
            assertTrue(elements[i] == curr.value)
            curr = curr.next
        }
        assertTrue(isEmptyListWithSentinel(list1))
        assertTrue(isEmptyListWithSentinel(list2))
    }

    @Test
    fun xor_equal_lists() {
        val elements = arrayListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val list1 = getListWithSentinel(0, 10, 1)
        val list2 = getListWithSentinel(0, 10, 1)
        assertTrue(isEmptyListWithSentinel(xor(list1, list2, CMP_NATURAL_ORDER)))
        var curr = list1.next
        for (i in elements.indices) {
            assertTrue(elements[i] == curr.value)
            curr = curr.next
        }
        curr = list2.next
        for (i in elements.indices) {
            assertTrue(elements[i] == curr.value)
            curr = curr.next
        }
    }

    @Test
    fun xor_mix_lists() {
        val elements = arrayListOf<Int>(3, 7, 13)
        val elements2 = arrayListOf<Int>(1, 5, 9)
        val list1 = getListWithSentinel(1, 10, 2)
        val list2 = getListWithSentinel(1, 15, 4)
        printListWithSentinel(list1)
        printListWithSentinel(list2)
        val res = xor(list1, list2, CMP_NATURAL_ORDER)
        var curr = res.next
        for (i in elements.indices) {
            assertTrue(elements[i] == curr.value)
            curr = curr.next
        }
        var curr1 = list1.next
        var curr2 = list2.next
        for (i in elements.indices) {
            assertTrue(elements2[i] == curr1.value)
            assertTrue(elements2[i] == curr2.value)
            curr1 = curr1.next
            curr2 = curr2.next
        }
    }
}






