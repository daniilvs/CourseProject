package test

import mybitset.BitSet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

//   В тестах and и or выдает не самую понятную ошибку:
//   org.opentest4j.AssertionFailedError: expected: mybitset.BitSet@3d99d22e<BitSet is [1, 0], size is 2> but was: mybitset.BitSet@49fc609f<BitSet is [1, 0], size is 2>
//     Expected :BitSet is [1, 0], size is 2
//     Actual   :BitSet is [1, 0], size is 2

class Tests {
    private val testBitSet1 =
        BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)


    @Test
    fun and() {
        assertEquals(BitSet(mutableListOf(1,0), 2), testBitSet1
            .and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), 8)))
        assertEquals(BitSet(mutableListOf(1,0,1,1), 4), testBitSet1
            .and(BitSet(mutableListOf(0,1,1,0,1,0,1,1), 8)))
        assertThrows(java.lang.NegativeArraySizeException::class.java) { testBitSet1
            .and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), 0))}
        assertThrows(java.lang.NegativeArraySizeException::class.java) { testBitSet1
            .and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), -6))}
        assertThrows(java.lang.IllegalArgumentException::class.java) { testBitSet1
            .and(BitSet(mutableListOf(0,1,1,0,0,2,1,0), 8))}
    }

    @Test
    fun or() {
        assertEquals(BitSet(mutableListOf(1,0,1,0,0,0,1,1,1,1), 10), testBitSet1
            .or(BitSet(mutableListOf(1,0,0), 3)))
    }

    @Test
    fun find() {
        assertFalse(testBitSet1.find(3, 1))
        assertTrue(testBitSet1.find(0, 1))
        assertThrows(java.lang.IllegalArgumentException::class.java) { testBitSet1.find(-1, 6)}
    }

}