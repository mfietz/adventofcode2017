import java.io.File

fun main(args: Array<String>) {
    val input = File("src/stepsPerInsert/13.txt").readLines()
    val firewall = Firewall(input)
    println(firewall.tripSeverity())
    println(firewall.delay())
}


class Firewall(input: List<String>) {

    val layers: IntArray

    init {
        val temp = input.map { it.split(": ") }
                .map { Pair(it[0].toInt(), it[1].toInt()) }.toMap()
        layers = IntArray((temp.keys.max() ?: -1) + 1)
        temp.forEach { k, v -> layers[k] = v }
    }

    fun position(depth: Int, time: Int): Int? {
        val range = layers[depth]
        if (range == 0) return null
        val i = time % (2 * range - 2)
        if (i >= range) {
            return (range - 1 ) - (i - (range-1))
        } else return i
    }

    fun tripSeverity(delay: Int = 0): Int {
        return (0 until layers.size).fold(0, { acc, depth ->
            if(position(depth, delay + depth) == 0) acc + depth * layers[depth]
            else acc
        })
    }

    fun caught(delay: Int): Boolean {
        (0 until layers.size).forEach { depth ->
            if(position(depth, delay + depth) == 0) return true
        }
        return false
    }

    fun delay(): Int {
        var delay = -1
        do {
            delay += 1
        } while (caught(delay))
        return delay
    }

}