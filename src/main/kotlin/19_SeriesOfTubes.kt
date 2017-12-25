class SeriesOfTubes(val input: List<String>) {

    var currentPos: Pos = Pos(input[0].indexOfFirst { it == '|' }, 0)
    val path = mutableListOf(currentPos)
    private val height = input.size
    private val width = input[0].length

    fun path(): String {
        while(next()) {}
        return path.map { input.get(it) }.filter { it.isLetter() }.joinToString("")
    }

    fun next(): Boolean {
        val(x,y) = currentPos
        val next = setOf(Pos(x-1, y), Pos(x + 1, y), Pos(x,  y -1 ), Pos(x, y + 1))
                .filter { 0 <= it.x && it.x < width && 0 <= it.y && it.y < height }
                .filter { !path.contains(it) }
                .firstOrNull { input.get(it) != ' ' }
        if(next != null) {
            currentPos = next
            path += currentPos
            return true
        }
        return false
    }

    private fun List<String>.get(pos: Pos) = this[pos.y][pos.x]

}

data class Pos(val x: Int, val y: Int)