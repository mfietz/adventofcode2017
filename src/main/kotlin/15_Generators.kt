
fun main(args: Array<String>) {
    val judge = JudgeFinalCount(Generator(16807, 591), Generator(48271, 393))
    println(judge.finalCount(40_000_000))

    val judge2 = JudgeFinalCount(Generator(16807, 591, 4), Generator(48271, 393, 8))
    println(judge2.finalCount(5_000_000))
}

private fun Long.divisiblyBy(divisor: Int) = this % divisor == 0L


class Generator(val factor: Int, var value: Long, val valueMustBeDividibleBy: Int = 1) {

    fun next(): Long {
        do {
            value = (value * factor) % 2147483647
        } while(!value.divisiblyBy(valueMustBeDividibleBy))
        return value
    }

}

class Binary(val value: Long) {

    override fun toString(): String {
        return value.toString(2).padStart(32, '0')
    }

    fun lowest16BitMatchWith(other: Binary): Boolean {
        return toString().substring(16) == other.toString().substring(16)
    }

}

class JudgeFinalCount(val generatorA: Generator, val generatorB: Generator) {

    fun finalCount(numPairs: Int): Int {
        return (1..numPairs).map {
            val bin1 = Binary(generatorA.next())
            val bin2 = Binary(generatorB.next())
            if (bin1.lowest16BitMatchWith(bin2)) 1 else 0
        }.sum()
    }

}

