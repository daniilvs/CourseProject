@file:Suppress("UNUSED_PARAMETER", "unused", "NAME_SHADOWING")

package mybitset

import java.lang.IllegalArgumentException



// data structure
class BitSet(private var list:MutableList<Int>, private var length: Int) {

    init {
        if ((length < 0) || (length != list.size))
            throw NegativeArraySizeException("Wrong length (length < 0 or length doesn't equal to the actual size of bitset")
        if (!listOf(0, 1).containsAll(list))
            throw IllegalArgumentException("Wrong input data (bitset has elements that aren't allowed")
    }

    /**   and logical function   */
    fun and(second: BitSet): BitSet{
        equals(second)
        for (i in 0..(length - 1)) {
            if (list[i] == second.list[i] && list[i] == 1) {
                continue
            }
            else
                list[i] = 0
        }
        unZero()
        return this
    }

    /**   or logical function   */
    fun or(second: BitSet): BitSet {
        equals(second)
        for (i in 0..(length - 1)) {
            if (list[i] == 0 && second.list[i] == 0) {
                continue
            }
            else
                list[i] = 1
        }
        unZero()
        return this
    }

    /**   not logical function   */
    fun not(): BitSet {
        val sup = mutableListOf<Int>()
        for (element in this.list) {
            if (element == 0)
                sup.add(1)
            else
                sup.add(0)
        }
        sup.reversed()
        this.list = sup
        unZero()
        return this
    }

    /**   find element   */
    fun find(index: Int, element: Int): Boolean {
        correctAll(element, index)
        return list[index] == element
    }

    /**   put element/elements   */
    /* for one element */
    fun put(index: Int, element: Int): BitSet {
        correctAll(element, index)
        list.add(index, element)
        length++
        return this
    }

    /* for array */
    fun put(index: Int, elements: List<Int>): BitSet {
        correctInput(elements)
        correctIndex(index)
        list.addAll(index, elements)
        length += elements.size
        return this
    }

    /**   delete element/elements    */
    /* for one element */
    fun delete(index: Int): BitSet {
        correctIndex(index)
        list.removeAt(index)
        length--
        return this
    }

    /* for array */
    fun delete(index: Int, elements: List<Int>): BitSet {
        correctIndex(index)
        for (element in elements) {
            delete(index)
        }
        return this
    }

    /**   equate bitsets (add insignificant zero in the beginning)   */
    private fun equals(second: BitSet) {
        if (length < second.length) {
            var diff = second.length - length
            length += diff
            list.reverse()
            do {
                list.add(0)
                diff--
            } while (diff > 0)
            list.reverse()
        }
        else {
            var diff = length - second.length
            second.length += diff
            second.list.reverse()
            do {
                second.list.add(0)
                diff--
            } while (diff > 0)
            second.list.reverse()
        }
    }

    /**   delete insignificant zeros in the beginning of the bitset   */
    private fun unZero(): BitSet {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            if (element == 0) {
                iterator.remove()
                length -= 1
            } else break

        }
        return this
    }

    /**   Is index correct? ;)   */
    private fun correctIndex(index: Int) {
        if (index !in 0..(length - 1))
            throw IllegalArgumentException("Wrong index")
    }

    /**   Is element correct?   */
    /* for one element */
    private fun correctInput(element: Int) {
        if (!(listOf(0, 1).contains(element)))
            throw IllegalArgumentException("Wrong input data")
    }

    /* for array */
    private fun correctInput(elements: List<Int>) {
        if (!(listOf(0, 1).containsAll(elements)))
            throw IllegalArgumentException("Wrong input data")
    }

    private fun correctAll(element: Int, index: Int) {
        correctInput(element)
        correctIndex(index)
    }

    override fun toString(): String {
        return "BitSet is $list, size is $length"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitSet

        if (list != other.list) return false
        if (length != other.length) return false

        return true
    }

    override fun hashCode(): Int {
        var result = list.hashCode()
        result = 31 * result + length
        return result
    }
}