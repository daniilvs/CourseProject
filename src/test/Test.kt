package test

import mybitset.BitSet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun and() {
        var testBitSet =
            BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertEquals(BitSet(mutableListOf(1,0), 2),
            testBitSet.and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), 8)))
        testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertEquals(BitSet(mutableListOf(1,0,1,1), 4),
            testBitSet.and(BitSet(mutableListOf(0,1,1,0,1,0,1,1), 8)))
        testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertThrows(java.lang.NegativeArraySizeException::class.java) {
            testBitSet.and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), 0)) }
        testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertThrows(java.lang.NegativeArraySizeException::class.java) {
            testBitSet.and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), -6)) }
        testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertThrows(java.lang.IllegalArgumentException::class.java) {
            testBitSet.and(BitSet(mutableListOf(0,1,1,0,0,2,1,0), 8)) }
    }

    @Test
    fun or() {
        var testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertEquals(BitSet(mutableListOf(1,0,1,0,0,0,1,1,1,1), 10),
            testBitSet.or(BitSet(mutableListOf(1,0,0), 3)))
        testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertThrows(java.lang.NegativeArraySizeException::class.java) {
            testBitSet.and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), 0)) }
        testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertThrows(java.lang.NegativeArraySizeException::class.java) {
            testBitSet.and(BitSet(mutableListOf(0,1,1,0,0,0,1,0), -6)) }
    }

    @Test
    fun find() {
        val testBitSet =
            BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertFalse(testBitSet.find(3, 1))
        assertTrue(testBitSet.find(0, 1))
        assertThrows(java.lang.IllegalArgumentException::class.java) {
            testBitSet.find(-1, 6) }
    }

    @Test
    fun not() {
        var testBitSet = BitSet(mutableListOf(1,0,1,0,0,0,1,0,1,1), 10)
        assertEquals(BitSet(mutableListOf(1,0,1,1,1,0,1,0,0), 9), testBitSet.not())
        testBitSet = BitSet(mutableListOf(0,1,0,1,1), 5)
        assertEquals(BitSet(mutableListOf(1,0,1,0,0), 5), testBitSet.not())

    }
}