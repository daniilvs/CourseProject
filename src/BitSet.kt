package mybitset

class BitSet(var list: MutableList<String>, var setLength: Int) {

    /**
     * or logical function
     */
    fun bitOr(first: BitSet, second: BitSet): MutableList<String> {
        val resOr = mutableListOf<String>()
        if (first.setLength > second.setLength) {
            for (element in second.list) {
                if (first.list.contains(element))
                    resOr.add(element)
            }
        } else {
            for (element in first.list) {
                if (second.list.contains(element))
                    resOr.add(element)
            }

        }
        return resOr
    }

    /**
     * and logical function
     */
    fun bitAnd(first: BitSet, second: BitSet): MutableList<String> {
        val resAnd = mutableListOf<String>()
        if (first.setLength > second.setLength) {
            for (element in second.list) {
                if (first.list.contains(element))
                    first.list.remove(element)
            }
            first.list.addAll(second.list)
            resAnd.addAll(first.list)
        } else {
            for (element in first.list) {
                if (second.list.contains(element))
                    second.list.remove(element)
            }
            first.list.addAll(second.list)
            resAnd.addAll(first.list)
        }
        return resAnd
    }

    /**
     * add logical function
     */
    fun bitAdd(first: BitSet, second: BitSet): MutableList<String> {
        for (element in second.list) {
            if (first.list.contains(element))
                first.list.remove(element)
        }
        return first.list
    }

    /**
     * xor logical function
     */
    fun bitXor(first: BitSet, second: BitSet): MutableList<String> {
        val resXor = mutableListOf<String>()
        if (first.setLength > second.setLength) {
            for (element in second.list) {
                if (first.list.contains(element)) {
                    first.list.remove(element)
                    second.list.remove(element)
                }
            }
        } else {
            for (element in first.list) {
                if (second.list.contains(element)) {
                    first.list.remove(element)
                    second.list.remove(element)
                }
            }
        }
        return resXor
    }

    /**
     * add elements
     */
}