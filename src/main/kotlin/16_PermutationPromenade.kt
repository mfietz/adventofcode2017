import java.io.File

fun main(args: Array<String>) {
    val input = File("src/stepsPerInsert/16.txt").readText().split(",")
    val programs = "abcdefghijklmnop"
    val programsArray = programs.toCharArray()

    var dance = Dance(programs.toCharArray(), input)
    dance.dance()
    println(dance.order)

    val positions= mutableListOf<String>()
    dance = Dance(programs.toCharArray(), input)
    var cycle: Int? = null
    var i = 0
    while (cycle == null) {
        i += 1
        dance.dance()
        val currentOrder = dance.orderAsString()
        if(currentOrder == programs) cycle = i
    }

    dance = Dance(programs.toCharArray(), input)
    for(i in 1..(1_000_000_000 % cycle)) {
        dance.dance()
    }
    println(dance.order)
}


class Dance(val order: CharArray, val moves: List<String>) {

    constructor(order: String, moves: List<String>) : this(order.toCharArray(), moves)

    fun dance() {
        moves.forEach { move ->
            when(move[0]) {
                's' -> order.spin(move.substring(1).toInt())
                'x' -> order.exchange(move.substring(1, move.indexOf('/')).toInt(), move.substringAfter('/').toInt())
                'p' -> order.exchange(order.indexOf(move[1].toLowerCase()), order.indexOf(move[3].toLowerCase()))
            }
        }
    }

    fun orderAsString(): String = order.joinToString("")

    private fun CharArray.spin(times: Int) {
        (1..times).forEach {
            val last = this[size-1]
            for(i in size-1 downTo  1) {
                this[i] = this[i-1]
            }
            this[0] = last
        }
    }

    private fun CharArray.exchange(onePosition: Int, otherPosition: Int) {
        val tmp = this[onePosition]
        this[onePosition] = this[otherPosition]
        this[otherPosition] = tmp
    }

}