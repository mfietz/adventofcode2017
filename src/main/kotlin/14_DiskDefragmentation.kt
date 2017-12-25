class Disc(val input: String) {

    val usedSquares: Int by lazy {
        squares.map { binary -> binary.count { it == '#' } }.sum()
    }

    val regions: Int by lazy {
        var regions = 0
        val grid = squares.joinToString("").toCharArray()
        for (index in 0 until grid.size) {
            if (mark(grid, index)) {
                regions += 1
            }
        }
        regions
    }

    private val squares: List<String> by lazy {
        (0..127).map {
            val hash = KnotHash(input + "-" + it).hash
            hexToBinary(hash)
        }
    }

    private fun mark(grid: CharArray, index: Int): Boolean {
        if (grid[index] != '#') return false
        grid[index] = '.'
        if (index - 128 >= 0) mark(grid, index - 128)
        if (index % 128 - 1 >= 0) mark(grid, index - 1)
        if (index % 128 + 1 < 128) mark(grid, index + 1)
        if (index + 128 < grid.size) mark(grid, index + 128)
        return true
    }

    private fun hexToBinary(hex: String): String {
        fun Char.hexToBinary(): String {
            return when (this) {
                '0' -> "...."
                '1' -> "...#"
                '2' -> "..#."
                '3' -> "..##"
                '4' -> ".#.."
                '5' -> ".#.#"
                '6' -> ".##."
                '7' -> ".###"
                '8' -> "#..."
                '9' -> "#..#"
                'a' -> "#.#."
                'b' -> "#.##"
                'c' -> "##.."
                'd' -> "##.#"
                'e' -> "###."
                'f' -> "####"
                else -> throw IllegalArgumentException()
            }
        }
        return hex.map { it.hexToBinary() }.joinToString("")
    }
}
