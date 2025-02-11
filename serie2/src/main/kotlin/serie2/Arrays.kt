package serie2


fun median(v: IntArray, l: Int, r: Int): Int {
    val mid = (l+r).floorDiv(2)
    var i: Int? = null
    do {
        i = when{
            i == null -> partition(v, l, r)
            i < mid -> partition(v, i+1, r)
            else -> partition(v, l, i-1)
        }
    }while (mid != i)
    return if (v.size% 2 != 0) v[mid] else (v[mid] + v[mid+1])/2
}
fun partition(a: IntArray, left: Int, right: Int): Int {
    var i = left - 1
    var j = right
    val pivot = a[right]
    while (true) {
        while (i < right && a[++i] < pivot);
        while (j > left && a[--j] > pivot);
        if (i >= j) break
        exchange(a, i, j)
    }
   exchange(a, i, right)
    return i
}

fun  exchange(a: IntArray, i: Int, j: Int) {
    val x = a[i]
    a[i] = a[j]
    a[j] = x
}
