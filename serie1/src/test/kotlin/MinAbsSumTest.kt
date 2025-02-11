import kotlin.test.*

class TestMinAbsSum {

    @Test
    fun testEmpty() = assertNull(minAbsSum(emptyArray()))
    @Test
    fun testOne() = assertNull(minAbsSum(arrayOf(2)))
    @Test
    fun testTwo() {
        assertEquals(Pair(-1, 2), minAbsSum(arrayOf(-1, 2)))
        assertEquals(Pair(-1, 1), minAbsSum(arrayOf(-1, 1)))
        assertNull( minAbsSum(arrayOf(1, 1)))
    }

    @Test
    fun testAllNegatives() {
        assertEquals(Pair(-5, -4), minAbsSum(arrayOf(-5, -4)))
        assertEquals(Pair(-2, -1), minAbsSum(Array<Int>(6){-(6-it)}))
    }

    @Test
    fun testAllPositives() {
        assertEquals(Pair(2, 3), minAbsSum(arrayOf(2, 3)))
        assertEquals(Pair(0, 1), minAbsSum(Array<Int>(6){it}))
    }

    @Test
    fun testMinAbsSum() {
//        val test = minAbsSum(arrayOf(-6, -5, -4, 0, 2, 4, 9))
        assertEquals(Pair(-6, 7), minAbsSum(arrayOf(-6, -5, 7, 8, 9)))

        var res :Pair<Int, Int>? = minAbsSum( arrayOf(-6, -5, -3, 0, 2, 4, 9))
        assertTrue( res == Pair(-5, 4) || res== Pair(-3, 2))

         assertEquals(Pair(-4, 4), minAbsSum(arrayOf(-6, -5, -4, 0, 2, 4, 9)))

        assertNull( minAbsSum( Array<Int>( 3) {5}))
        assertNull( minAbsSum( Array<Int>( 6) {-5} ))
    }
}
