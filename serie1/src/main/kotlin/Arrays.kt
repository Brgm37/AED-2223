data class Point(var x:Int, var y:Int)

fun count(v: Array<Int>, l: Int, r: Int, element: Int): Int =
    if (l > r)
        0
    else{
        val mid = (l + r)/2
        val midEl = v[mid]
        if (midEl == element){
            val lCount = findLeftLimitBinary(v, l, mid, element)
            val rCount = findRightLimitBinary(v, mid, r, element)
            rCount-lCount+1
        }
        else if (midEl < element)
            count(v, mid+1, r, element)
        else
            count(v, l, mid-1, element)
    }

fun findRightLimitBinary(v:Array<Int>, l: Int, r: Int, element: Int): Int{
    if (l == r) return r
    val mid = (l+r)/2
    return if (v[mid] != element)
        findRightLimitBinary(v, l, mid-1, element)
    else {
        if (mid+1 > r) mid
        else {
            val boundary = v[mid + 1]
            if (boundary > element)
                mid
            else
                findRightLimitBinary(v, mid + 1, r, element)
        }
    }
}
fun findLeftLimitBinary(v:Array<Int>, l: Int, r: Int, element: Int): Int{
    if (r == l ) return l
    val mid = (l+r)/2
    return if (v[mid] != element)
        findLeftLimitBinary(v, mid+1, r, element)
    else {
        if (mid-1 < l) mid
        else {
            val boundary = v[mid - 1]
            if (boundary < element)
                mid
            else
                findLeftLimitBinary(v, l, mid - 1, element)
        }
    }
}
fun minAbsSum(ar:Array<Int>): Pair<Int, Int>? {
    if (ar.size <= 1) return null
    var inIdx = 0
    var finIdx = ar.lastIndex
    var minPar: Pair<Int, Int>
    if (ar[finIdx] <= 0)
        minPar = Pair(ar[finIdx-1], ar[finIdx])
    else if (ar[inIdx] >= 0)
        minPar = Pair(ar[inIdx], ar[1])
    else {
        var vector = ar[inIdx]+ar[finIdx]
        vector = if (vector<0) -vector else vector
        minPar = Pair(ar[inIdx], ar[finIdx])
        var n = 1
        finIdx--
        while (n < ar.lastIndex) {
            var cmp = ar[inIdx]+ar[finIdx]
            val flag = if (cmp<0) 1 else 0
            cmp = if (cmp<0) - cmp else cmp
            if (cmp<vector) {
                vector = cmp
                minPar = Pair(ar[inIdx], ar[finIdx])
                finIdx--
            }
            else {
                if (flag != 0 && cmp > vector) {
                    inIdx++
                    finIdx++
                }
                else
                    finIdx--
            }
            if (inIdx == finIdx)
                break
            n++
        }
    }
    return  if (minPar.first != minPar.second ) minPar else null
}

fun countSubKSequences(a: Array<Int>, k: Int): Int {
    var n = 0
    var test = 0
    var count = 0
    var finalSubK = 0
    var int = 0
    while (n < a.size){
        if (count == 0)
            int = a[n]
        val el = a[n]
        if (k == 1 || (n < a.lastIndex && el <=  a[n+1]) || (count == k-1 && el >= a[n-1]))
            test+= el
        else{
            test = 0
            count = 0
        }
        count++
        if (count == k){
            if(test%k == 0)
                finalSubK++
            count = k-1
            test -= int
            int = a[if (k == 1) n else  n-(k-2)]
        }
        n++
    }
    return finalSubK
}

fun countEquals( points1: Array<Point>, points2: Array<Point>, cmp: (p1:Point, p2:Point )-> Int): Int {
    var finalCount = 0
    var n = 0
    var point1Idx = 0
    var point2Idx = 0
    if (points1.size == 0 || points2.size == 0) return finalCount
    if (points1.size >= points2.size) {
        while (n < points1.size){
            val cmp1 = cmp(points1[point1Idx], points2[point2Idx])
            if (cmp1 == 0) {
                finalCount++
                point1Idx++
                point2Idx = if (point2Idx+1 > points2.lastIndex) point2Idx else point2Idx+1
            }
            else if (cmp1 < 0)
                point1Idx++
            else
                point2Idx = if (point2Idx+1 > points2.lastIndex) point2Idx else point2Idx+1
            n++
        }
    } else {
        while (n < points2.size){
            val cmp2 = cmp(points1[point1Idx], points2[point2Idx])
            if (cmp2 == 0) {
                finalCount++
                point1Idx = if (point1Idx+1 > points1.lastIndex) point1Idx else point1Idx+1
                point2Idx++
            }
            else if (cmp2 < 0)
                point1Idx = if (point1Idx+1 > points1.lastIndex) point1Idx else point1Idx+1
            else
                point2Idx++
            n++
        }
    }
    return finalCount
}





