package serie2

import kotlin.test.*

class TestStreamMedian {
    val N= 10
    @Test
    fun testOne() {
        val s= StreamMedian()
        s.updateSet( 1 )
        assertEquals(1, s.getMedian())
    }
    @Test
    fun testTwo() {
        val s= StreamMedian()
        s.updateSet( 1 )
        s.updateSet( 3 )
        assertEquals(2, s.getMedian())
    }
    @Test
    fun median_onArrayWithImparNumberOfElements(){
        val array = arrayOf(20, 2, 10, 9, 7, 6, 5, 4, 30, 50, 60, 1, 0)
        val s= StreamMedian()
        for (v in array)
            s.updateSet( v )
        assertEquals(7, s.getMedian())
    }

    @Test
    fun median_onArrayWithParNumberOfElements(){
        val array = intArrayOf(20, 2, 10, 9, 7, 6, 5, 4, 30, 50, 60, 1, 0, 70)
        val s= StreamMedian()
        for (v in array)
            s.updateSet( v )
        assertEquals(8, s.getMedian())
    }

    @Test
    fun testThreeCrescent() {
        val s= StreamMedian()
        s.updateSet( 1 )
        s.updateSet( 2 )
        s.updateSet( 3 )
        assertEquals(2, s.getMedian())
    }
    @Test
    fun testThreeDecrescent() {
        val s= StreamMedian()
        s.updateSet( 1 )
        s.updateSet( 3 )
        s.updateSet( 0 )
        assertEquals(1, s.getMedian())
    }

    @Test
    fun testCrescent() {
        val s= StreamMedian()
        for( i in 1 .. N) {
            s.updateSet(i)
            assertEquals((i + 1)/2, s.getMedian())
        }
    }

    @Test
    fun testConsecutivos() {
        val s= StreamMedian()
        for( i in N downTo 1) {
            s.updateSet(i)
            assertEquals((N+i)/2, s.getMedian())
        }
    }

 }
