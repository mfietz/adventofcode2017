import java.io.File

class StreamProcessor(private val input: String) {

    val totalScore: Int
    val nonCanceledCharactersWithinGarbage: Int

    init {
        var totalScore = 0
        var garbageCount = 0
        var groupScore = 0
        var garbage = false
        var ignore = false
        input.forEach {
            if(ignore) {
                ignore = false
                return@forEach
            }
            when(it) {
                '{' -> if(garbage) garbageCount += 1 else groupScore += 1
                '}' -> {
                    if(garbage) {
                        garbageCount+=1
                        return@forEach
                    }
                    totalScore += groupScore
                    groupScore -= 1
                }
                '<' -> if(garbage) garbageCount += 1 else garbage = true
                '>' -> garbage = false
                '!' -> ignore = true
                else -> if(garbage) garbageCount += 1
            }
        }
        this.totalScore = totalScore
        this.nonCanceledCharactersWithinGarbage = garbageCount
    }

    private class State(
            val currentGroupScore: Int,
            val garbage: Boolean
    ) {

        fun groupStart() = State(currentGroupScore + 1, garbage)

    }

}

fun main(args: Array<String>) {
    val input = File("src/09_input.txt").readText()
    println(StreamProcessor(input).totalScore)
    println(StreamProcessor(input).nonCanceledCharactersWithinGarbage)
}