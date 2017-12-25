import kotlin.math.absoluteValue
import kotlin.math.sign

class HexEd(val input: String) {

    fun steps(): Int {
        val res = input.split(',').map { toVector(it) }
                .fold(Pair(0,0)) { acc , p -> Pair(acc.first + p.first,acc.second + p.second) }
        if(res.first.sign == res.second.sign) {
            return Math.max(res.first.absoluteValue, res.second.absoluteValue)
        } else {
            return res.first.absoluteValue + res.second.absoluteValue
        }
    }

    private fun toVector(input: String) = when (input) {
        "n" -> Pair(1, 1)
        "s" -> Pair(-1, -1)
        "nw" -> Pair(1, 0)
        "ne" -> Pair(0, 1)
        "sw" -> Pair(0, -1)
        "se" -> Pair(-1, 0)
        else -> throw IllegalArgumentException("Unknown stepsPerInsert")
    }

}