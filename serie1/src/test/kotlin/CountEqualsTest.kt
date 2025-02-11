import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

    class CountEqualsTest {

        private val cmpPoints = { a: Point, b: Point ->
            if (a.x != b.x) a.x - b.x
            else a.y - b.y
        }

        @Test
        fun countEquals_EmptyArray(){
            val array1 = emptyArray<Point>()
            val array2 = arrayOf(Point(0, 0))
            assertEquals(0, countEquals(array1, array1, cmpPoints))
            assertEquals(0, countEquals(array1, array2, cmpPoints))
            assertEquals(0, countEquals(array2, array1, cmpPoints))
        }

        @Test
        fun countEquals_SingletonPointArray(){
            val array1 = arrayOf<Point>(Point(0, 0))
            val array2 = arrayOf<Point>(Point(1, 0))
            val array3 = arrayOf<Point>(Point(0, 1))
            assertEquals(1, countEquals(array1, array1, cmpPoints))
            assertEquals(0, countEquals(array1, array2, cmpPoints))
            assertEquals(0, countEquals(array1, array3, cmpPoints))
        }

        @Test
        fun countEquals_MultiplePointsAllEqualsArray(){
            val array1 = arrayOf<Point>(Point(0, 0), Point(0, 2), Point(1, 2),
                Point(1, 3), Point(2, 0), Point(2, 2))
            val array2 = arrayOf<Point>(Point(0, 3), Point(0, 5), Point(2, 1))
            assertEquals(6, countEquals(array1, array1, cmpPoints))
            assertEquals(3, countEquals(array2, array2, cmpPoints))
        }

        @Test
        fun countEquals_MultiplePointsArray(){
            val array1 = arrayOf<Point>(Point(0, 0), Point(0, 2), Point(1, 2),
                Point(1, 3), Point(2, 0), Point(2, 2))
            val array2 = arrayOf<Point>(Point(0, 2), Point(1, 3), Point(2, 1))
            val array3 = arrayOf<Point>(Point(0, 0), Point(0, 1), Point(0, 2),
                Point(0, 3), Point(1, 1), Point(1, 2), Point(1, 3), Point(2, 1))
            assertEquals(2, countEquals(array1, array2, cmpPoints))
            assertEquals(2, countEquals(array2, array1, cmpPoints))
            assertEquals(3, countEquals(array2, array3, cmpPoints))
            assertEquals(3, countEquals(array3, array2, cmpPoints))
            assertEquals(4, countEquals(array1, array3, cmpPoints))
        }
    }

