fun bubbleSort(pairsList: List<Pair<String, String>>, linearSearchDuration: Long): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()
    result.addAll(pairsList)
    val sortStart = System.currentTimeMillis()
    var sortFinish = 0L
    for (i in pairsList.indices) {
        for (j in i until result.size) {
            val (_, iSubscriber) = result[i]
            val (_, jSubscriber) = result[j]
            if (iSubscriber > jSubscriber) {
                val temp = result[i]
                result[i] = result[j]
                result[j] = temp
            }
        }
        sortFinish = System.currentTimeMillis()
        if (sortFinish - sortStart > linearSearchDuration * 10) {
            SORT_WAS_STOPPED = true
            break
        }
    }
    BUBBLE_SORT_START = sortStart
    BUBBLE_SORT_FINISH = sortFinish
    return result
}

fun quickSort(pairsList: List<Pair<String, String>>): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()
    val leftPart = mutableListOf<Pair<String, String>>()
    val rightPart = mutableListOf<Pair<String, String>>()
    val pivot = pairsList[pairsList.lastIndex]
    val (_, pivotSub) = pivot
    for (i in 0 until pairsList.size - 1) {
        val (_, currentSub) = pairsList[i]
        if (currentSub < pivotSub) {
            leftPart.add(pairsList[i])
        } else {
            rightPart.add(pairsList[i])
        }
    }
    val sortedLeftPart = if (leftPart.size > 0) {
        quickSort(leftPart)
    } else {
        listOf()
    }
    val sortedRightPart = if (rightPart.size > 0) {
        quickSort(rightPart)
    } else {
        listOf()
    }
    result.addAll(sortedLeftPart)
    result.add(pivot)
    result.addAll(sortedRightPart)
    return result
}