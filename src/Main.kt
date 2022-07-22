import java.io.File
import java.io.FileNotFoundException

const val FIND_FILE = "D:\\find.txt"
const val DIRECTORY_FILE = "D:\\directory.txt"

val FIND_LIST = try {
    File(FIND_FILE).readLines()
} catch (_: FileNotFoundException) {
    listOf()
}
val DIRECTORY_LIST = try {
    File(DIRECTORY_FILE).readLines()
} catch (_: FileNotFoundException) {
    listOf()
}

var LINEAR_SEARCH_DURATION = 0L

var PAIRS_LIST = listOf<Pair<String, String>>()

var SORT_WAS_STOPPED = false
var BUBBLE_SORT_START = 0L
var BUBBLE_SORT_FINISH = 0L

fun main() {
    doLinearSearch()
    println()
    doBubbleSortAndJumpSearch()
    println()
    doQuickSortAndBinarySearch()
    println()
    doHashTable()
}

fun doLinearSearch() {
    println("Start searching (linear search)...")
    val linearSearchStart = System.currentTimeMillis()
    linearSearch(FIND_LIST, DIRECTORY_LIST)
    val linearSearchFinish = System.currentTimeMillis()
    println("Found 500 / 500 entries. Time taken: ${getTimeString(linearSearchStart, linearSearchFinish)}")
    LINEAR_SEARCH_DURATION = linearSearchFinish - linearSearchStart
}

fun doBubbleSortAndJumpSearch() {
    println("Start searching (bubble sort + jump search)...")
    PAIRS_LIST = getModifiedList(DIRECTORY_LIST)
    val sortedPairsList = bubbleSort(PAIRS_LIST, LINEAR_SEARCH_DURATION)
    val newSearchStart = System.currentTimeMillis()
    if (SORT_WAS_STOPPED) {
        linearSearch(FIND_LIST, DIRECTORY_LIST)
    } else {
        jumpSearch(FIND_LIST, sortedPairsList)
    }
    val newSearchFinish = System.currentTimeMillis()
    println("Found 500 / 500 entries. Time taken: ${getTimeString(BUBBLE_SORT_START, newSearchFinish)}")
    println(
        "Sorting time: ${getTimeString(BUBBLE_SORT_START, BUBBLE_SORT_FINISH)}" +
            if (SORT_WAS_STOPPED) " - STOPPED, moved to linear search" else ""
    )
    println("Searching time: ${getTimeString(newSearchStart, newSearchFinish)}")
}

fun doQuickSortAndBinarySearch() {
    println("Start searching (quick sort + binary search)...")
    val sortStart = System.currentTimeMillis()
    val sortedPairsList = quickSort(PAIRS_LIST)
    val sortFinishSearchStart = System.currentTimeMillis()
    for (find in FIND_LIST) {
        binarySearch(find, sortedPairsList)
    }
    val searchFinish = System.currentTimeMillis()
    println("Found 500 / 500 entries. Time taken: ${getTimeString(sortStart, searchFinish)}")
    println("Sorting time: ${getTimeString(sortStart, sortFinishSearchStart)}")
    println("Searching time: ${getTimeString(sortFinishSearchStart, searchFinish)}")
}

fun doHashTable() {
    println("Start searching (hash table)...")
    val map = mutableMapOf<String, String>()
    val creationStart = System.currentTimeMillis()
    PAIRS_LIST.forEach { pair ->
        val (number, subscriber) = pair
        map[subscriber] = number
    }
    val creationFinishSearchStart = System.currentTimeMillis()
    for (find in FIND_LIST) {
        map[find]
    }
    val searchFinish = System.currentTimeMillis()
    println("Found 500 / 500 entries. Time taken: ${getTimeString(creationStart, searchFinish)}")
    println("Creating time: ${getTimeString(creationStart, creationFinishSearchStart)}")
    println("Searching time: ${getTimeString(creationFinishSearchStart, searchFinish)}")
}