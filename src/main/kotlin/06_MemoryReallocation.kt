private val input = "4\t10\t4\t1\t8\t4\t9\t14\t5\t1\t14\t15\t0\t15\t3\t5"
var seen = mutableListOf<String>()

fun main(args: Array<String>) {
    val banks = input.split("\t").map { it.toInt() }.toMutableList()
    //val banks = mutableListOf(0,2,7,0)
    seen.add(banks.configuration())
    var cycles = 0
    while (true) {
        cycles += 1
        val indexOfMax = banks.indexOfMax()
        val toRedistribute = banks[indexOfMax]
        banks[indexOfMax] = 0
        var index: Int = indexOfMax
        for (i in 1..toRedistribute) {
            index = (index + 1) % banks.size
            banks[index] += 1

        }
        val configuration = banks.configuration()
        println(banks.configuration())
        if (seen.contains(configuration)) {
            println(seen.indexOf(configuration))
            println(cycles)
            return
        } else {
            seen.add(configuration)
        }
    }
}

private fun MutableList<Int>.configuration(): String {
    return this.joinToString("\t")
}

private fun List<Int>.indexOfMax(): Int {
    var indexOfMax = 0
    this.forEachIndexed { index, e ->
        if (this[index] > this[indexOfMax]) {
            indexOfMax = index
        }
    }
    return indexOfMax
}
