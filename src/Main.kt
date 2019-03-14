import mybitset.BitSet

fun main() {
    val first = BitSet(mutableListOf(1, 0, 1, 0), 4)
    val second = BitSet(mutableListOf(1, 1, 0, 0, 0), 5)
    println(first.and(second))
}