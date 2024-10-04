import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

fun change(amount: Long): Map<Int, Long> {
    require(amount >= 0) { "Amount cannot be negative" }
    
    val counts = mutableMapOf<Int, Long>()
    var remaining = amount
    for (denomination in listOf(25, 10, 5, 1)) {
        counts[denomination] = remaining / denomination
        remaining %= denomination
    }
    return counts
}

// Write your first then lower case function here
fun findFirstLowercase(strings: List<String>, predicate: (String) -> Boolean): String? {
    return strings.firstOrNull(predicate)?.toLowerCase()
// Write your say function here
class Say(private val phraseBuilder: StringBuilder) {

    // Method to chain words
    fun and(word: String): Say {
        phraseBuilder.append(" ").append(word)
        return this
    }

    // Read-only property to get the accumulated phrase
    val phrase: String
        get() = phraseBuilder.toString()
}

class Say(private val phraseBuilder: StringBuilder) {

    // Method to chain words, ensuring spaces are handled correctly
    fun and(word: String): Say {
        if (phraseBuilder.isNotEmpty() || word.isEmpty()) {
            phraseBuilder.append(" ")
        }
        phraseBuilder.append(word)
        return this
    }

    val phrase: String
        get() = phraseBuilder.toString() // Return the phrase exactly as is

    fun clone(): Say {
        return Say(StringBuilder(this.phraseBuilder.toString()))
    }
}

// Write your meaningfulLineCount function here

@Throws(IOException::class)
fun countValidLines(fileName: String): Long {
    return File(fileName).bufferedReader().use { reader ->
        reader.lineSequence()
            .filter { it.trim().isNotEmpty() }  // Ignore empty or whitespace-only lines
            .filter { !it.trim().startsWith("#") }  // Ignore lines starting with #
            .count()
    }
}

fun main() {
    try {
        val count = countValidLines("example.txt")
        println("Valid line count: $count")
    } catch (e: IOException) {
        println("Error reading file: ${e.message}")
    }
}

// Write your Quaternion data class here

// Write your Binary Search Tree interface and implementing classes here
