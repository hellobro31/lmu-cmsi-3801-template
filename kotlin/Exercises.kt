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
    // d
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
data class Quaternion(val a: Double, val b: Double, val c: Double, val d: Double) {

    companion object {
        val ZERO = Quaternion(0.0, 0.0, 0.0, 0.0)
        val I = Quaternion(0.0, 1.0, 0.0, 0.0)
        val J = Quaternion(0.0, 0.0, 1.0, 0.0)
        val K = Quaternion(0.0, 0.0, 0.0, 1.0)
    }

    fun coefficients(): List<Double> {
        return listOf(a, b, c, d)
    }

    operator fun plus(other: Quaternion): Quaternion {
        return Quaternion(a + other.a, b + other.b, c + other.c, d + other.d)
    }

    operator fun times(other: Quaternion): Quaternion {
        return Quaternion(
            a * other.a - b * other.b - c * other.c - d * other.d,
            a * other.b + b * other.a + c * other.d - d * other.c,
            a * other.c - b * other.d + c * other.a + d * other.b,
            a * other.d + b * other.c - c * other.b + d * other.a
        )
    }

    fun conjugate(): Quaternion {
        return Quaternion(a, -b, -c, -d)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        if (a != 0.0) builder.append(a)
        if (b != 0.0) builder.append((if (b > 0 && builder.isNotEmpty()) "+" else "") + (if (b == 1.0) "i" else if (b == -1.0) "-i" else "${b}i"))
        if (c != 0.0) builder.append((if (c > 0 && builder.isNotEmpty()) "+" else "") + (if (c == 1.0) "j" else if (c == -1.0) "-j" else "${c}j"))
        if (d != 0.0) builder.append((if (d > 0 && builder.isNotEmpty()) "+" else "") + (if (d == 1.0) "k" else if (d == -1.0) "-k" else "${d}k"))
        return if (builder.isEmpty()) "0" else builder.toString()
    }
}

// Write your Binary Search Tree interface and implementing classes here
sealed interface BinarySearchTree {
    fun size(): Int
    fun contains(value: String): Boolean
    fun insert(value: String): BinarySearchTree
    override fun toString(): String

    object Empty : BinarySearchTree {
        override fun size(): Int = 0
        override fun contains(value: String): Boolean = false
        override fun insert(value: String): BinarySearchTree = Node(value, Empty, Empty)
        override fun toString(): String = ""
    }

    data class Node(
        val value: String,
        val left: BinarySearchTree,
        val right: BinarySearchTree
    ) : BinarySearchTree {
        override fun size(): Int = 1 + left.size() + right.size()

        override fun contains(value: String): Boolean =
            when {
                value == this.value -> true
                value < this.value -> left.contains(value)
                else -> right.contains(value)
            }

        override fun insert(value: String): BinarySearchTree =
            when {
                value == this.value -> this
                value < this.value -> copy(left = left.insert(value))
                else -> copy(right = right.insert(value))
            }

        // Corrected toString method to properly format the parentheses
        override fun toString(): String {
            val leftStr = if (left is Node) left.toString() else ""
            val rightStr = if (right is Node) right.toString() else ""

            return when {
                leftStr.isEmpty() && rightStr.isEmpty() -> value
                leftStr.isNotEmpty() && rightStr.isEmpty() -> "($leftStr)$value"
                leftStr.isEmpty() && rightStr.isNotEmpty() -> "$value($rightStr)"
                else -> "($leftStr)$value($rightStr)"
            }
        }
    }
}