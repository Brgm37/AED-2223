import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CountTest {

        @Test
        fun count_onAnEmptyArray(){
            val v = emptyArray<Int>()
            assertEquals(0, count(v, 0, v.size-1, 3))
        }


        @Test
        fun count_withAlternateValues() {
            val v = Array(100){it%2}
            v.sort()
            assertEquals(50, count(v, 0, v.size-1, 0))
            assertEquals(50, count(v, 0, v.size-1, 1))
            assertEquals(0, count(v, 0, v.size-1, 2))
            assertEquals(0, count(v, 0, v.size-1, -1))
        }

        @Test
        fun count_withRepeatedValues_inSequence() {
            val v = arrayOf(0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2)
            assertEquals(7, count(v, 0, v.size - 1, 1))
            assertEquals(2, count(v, 0, v.size - 1, 0))
            assertEquals(4, count(v, 0, v.size - 1, 2))
        }
        @Test
        fun count_withDistinctValues() {
          val v = Array(100){it}
          for(i in v) {
//              val test = count(v, 0, v.size - 1, i)
              assertEquals(1, count(v, 0, v.size - 1, i))
          }
        }

        @Test
        fun count_withDistinctValues_NotIn() {
            val v = Array(20){2*it}
            for(i in v.indices) {
                assertEquals(0, count(v, 0, v.size - 1, 2*i+1))
            }
        }

}

