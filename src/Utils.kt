fun getModifiedList(directoryList: List<String>): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()
    for (entry in directoryList) {
        val tempArray = entry.split(" ")
        if (tempArray.size == 3) {
            result.add(Pair(tempArray[0], "${tempArray[1]} ${tempArray[2]}"))
        } else {
            result.add(Pair(tempArray[0], tempArray[1]))
        }
    }
    return result
}

fun getTimeString(start: Long, finish: Long): String {
    val totalSeconds = (finish - start) / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds - minutes
    val milliseconds = (finish - start) % 1000
    return "$minutes min. $seconds sec. $milliseconds ms."
}