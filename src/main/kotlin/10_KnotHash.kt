private val input = "130,126,1,11,140,2,255,207,18,254,246,164,29,104,0,224"

fun main(args: Array<String>) {
    val part1 = input.split(',').map { it.toInt() }
    println(KnotHash(part1).part1)
    println(KnotHash(input).hash)
}

class KnotHash(val input: List<Int>) {

    constructor(input: String) : this(input.map { it.toInt() })

    private val suflist = listOf(17, 31, 73, 47, 23)

    val part1: Int
        get() {
            val list = knot(input)
            return list[0] * list[1]
        }

    val hash: String
        get() {
            val list = knot(input + suflist, 64)
            var hash = ""
            for (block in 0 until 16) {
                val start = 16 * block
                val endExclusive = 16 * (block + 1)
                val slice = list.slice(start until endExclusive).toIntArray()
                val xor = Xor(slice).result
                hash += Hex(xor).toString()
            }
            return hash
        }

    private fun knot(input: List<Int>, times: Int = 1): IntArray {
        val list = IntArray(256) { it }
        var currentPos = 0
        var skipSize = 0
        for (round in 1..times) {
            input.forEachIndexed { index, length ->
                val slice = (list + list).slice(currentPos until currentPos + length).reversed()
                slice.forEachIndexed { i, value ->
                    val pos = (currentPos + i) % list.size
                    list[pos] = value
                }
                currentPos = (currentPos + length + skipSize) % list.size
                skipSize += 1
            }
        }
        return list
    }
}


class Xor(input: IntArray) {
    val result = input.reduce { acc, value -> acc xor value }
}

class Hex(val value: Int) {

    init {
        check(value < 256)
    }

    override fun toString(): String {
        return hex(value / 16) + hex(value % 16)
    }

    private fun hex(value: Int): String {
        check(value < 16)
        return when (value) {
            10 -> "a"
            11 -> "b"
            12 -> "c"
            13 -> "d"
            14 -> "e"
            15 -> "f"
            else -> value.toString()
        }
    }


}