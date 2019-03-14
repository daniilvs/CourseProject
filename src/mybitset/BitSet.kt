@file:Suppress("UNUSED_PARAMETER", "unused", "NAME_SHADOWING")

package mybitset

import java.lang.IllegalArgumentException

class BitSet(private val list:MutableList<Int>, private var length: Int) {
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
            if ((list[i] == second.list[i] && list[i] == 1) ||
                (list[i] == second.list[i] && list[i] == 0)) {
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

    /**   add logical function   */
    fun add(second: BitSet): BitSet = TODO() // Узнать, что именно подразумевается под "дополнением"
               // method invocation

    /**   find element   */
    fun find(index: Int, element: Int): Boolean {
        correctInput(element)
        correctIndex(index)
        return list[index] == element
    }

    /**   put element/elements   */
    // for one element
    fun put(index: Int, element: Int): BitSet {
        correctInput(element)
        correctIndex(index)
        list.add(index, element)
        length++
        return this
    }

    // for array
    fun put(index: Int, elements: List<Int>): BitSet {
        correctInput(elements)
        correctIndex(index)
        list.addAll(index, elements)
        length += elements.size
        return this
    }

    /**   delete element/elements    */
    // for one element
    fun delete(index: Int): BitSet {
        correctIndex(index)
        list.removeAt(index)
        length--
        return this
    }

    fun delete(index: Int, elements: List<Int>): Unit = TODO() // Что представляет из себя заданный массив элементов, который требуется удалить?

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
        if (index !in 0..(length-1))
            throw IllegalArgumentException("Wrong index")
    }

    /**   Is element correct?   */
    // for one element
    private fun correctInput(element: Int) {
        if (!(listOf(0, 1).contains(element)))
            throw IllegalArgumentException("Wrong input data")
    }

    // for array
    private fun correctInput(elements: List<Int>) {
        if (!(listOf(0, 1).containsAll(elements)))
            throw IllegalArgumentException("Wrong input data")
    }


    override fun toString(): String {
        return "BitSet is $list, size is $length"
    }

}