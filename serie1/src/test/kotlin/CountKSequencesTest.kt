
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CountSubKSequencesTest {
    @Test
    fun countSubKSequences_EmptyArray() {
        assertEquals(0, countSubKSequences(Array(0){0}, 1))
        assertEquals(0, countSubKSequences(Array(0){0}, 3))
    }

    @Test
    fun countSubKSequences_SingletonArray() {
        val a = arrayOf(1)
//        val test = countSubKSequences(a, 1)
        assertEquals(1, countSubKSequences(a, 1))
        assertEquals(0, countSubKSequences(a, 3))
    }

    @Test
    fun countSubKSequences_Example() {
        val a = arrayOf(1, 2, 3, 4, 1)
//        val test = countSubKSequences(a, 3)
        assertEquals(2, countSubKSequences(a, 3))
    }

    @Test
    fun countSubKSequences_AllEquals() {
        val a = arrayOf(3, 3, 3, 3, 3, 3, 3)
        assertEquals(7, countSubKSequences(a, 1))
        assertEquals(5, countSubKSequences(a, 3))
        assertEquals(0, countSubKSequences(a, 9))
    }
}