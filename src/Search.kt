import kotlin.math.roundToInt
import kotlin.math.sqrt

fun linearSearch(findList: List<String>, directoryList: List<String>) {
    for (find in findList) {
        for (entry in directoryList) {
            if (entry.contains(find)) {
                break
            }
        }
    }
}

fun jumpSearch(findList: List<String>, sortedPairsList: List<Pair<String, String>>) {
    val jump = sqrt(sortedPairsList.size.toDouble()).roundToInt()
    loop@ for (find in findList) {
        var beforeIndex = 0
        for (i in sortedPairsList.indices step jump) {
            val (_, subscriber) = sortedPairsList[i]
            when {
                find == subscriber -> break
                find > subscriber -> beforeIndex = i
                else -> {
                    for (j in beforeIndex until sortedPairsList.size) {
                        val (_, almostSubscriber) = sortedPairsList[j]
                        if (find == almostSubscriber) {
                            continue@loop
                        }
                    }
                }
            }
        }
    }
}

fun binarySearch(find: String, list: List<Pair<String, String>>) {
    val (_, middleSub) = list[list.size / 2]
    when {
        middleSub == find -> return
        find < middleSub -> binarySearch(find, list.subList(0, list.size / 2))
        else -> binarySearch(find, list.subList(list.size / 2, list.size))
    }
}