import kotlin.math.absoluteValue

private const val input = 277678

fun main(args: Array<String>) {
    part1()
    part2()
}

private fun part1() {
    println("### PART 1 ###")
    var size = 3
    var memory: SpiralMemory
    do {
        memory = SpiralMemory(size)
        size += 2
    } while (memory.getLast() < input)
    println()
    println(memory)
    println("Solution: ${memory.getSolution(input)}\n")
}

private fun part2() {
    println("### PART 2 ###")
    var size = 3
    var memory: SecondSpiralMemory
    do {
        memory = SecondSpiralMemory(size)
        size += 2
    } while (memory.getLast() < input)
    println()
    println(memory)
    println("Solution: " + memory.getSolution(input))
}

open class SpiralMemory(val size: Int) {

    val memory = Array(size, { IntArray(size) })
    val center = (size - 1) / 2;

    init {
        check(size % 2 == 1)
        var start = Pair(center, center)
        memory[center][center] = nextValue(center, center)
        do {
            start = goRight(goDown(goLeft(goUp(goRightOnce(start)))))
        } while (start.first < size - 1)
    }

    fun getLast(): Int = memory[size - 1][size - 1]

    internal open fun getSolution(value: Int): Int? {
        for (y in 0 until size) {
            for (x in 0 until size) {
                if (memory[x][y] == value) {
                    return (x - center).absoluteValue + (y - center).absoluteValue
                }
            }
        }
        return null;
    }

    private fun goRightOnce(start: Pair<Int, Int>): Pair<Int, Int> {
        val (x, y) = start
        memory[x + 1][y] = nextValue(x + 1, y)
        return Pair(x + 1, y)
    }

    private fun goRight(start: Pair<Int, Int>): Pair<Int, Int> {
        var (x, y) = start
        do {
            x += 1
            memory[x][y] = nextValue(x, y)
        } while ((y < size - 1 && memory[x + 1][y - 1] != 0) || (y == size - 1 && x < size - 1))
        return Pair(x, y)
    }

    private fun goUp(start: Pair<Int, Int>): Pair<Int, Int> {
        var (x, y) = start
        do {
            y -= 1
            memory[x][y] = nextValue(x, y)
        } while (y > 0 && memory[x - 1][y] != 0)
        return Pair(x, y)
    }

    private fun goLeft(start: Pair<Int, Int>): Pair<Int, Int> {
        var (x, y) = start
        do {
            x -= 1
            memory[x][y] = nextValue(x, y)
        } while (x > 0 && memory[x][y + 1] != 0)
        return Pair(x, y)
    }

    private fun goDown(start: Pair<Int, Int>): Pair<Int, Int> {
        var (x, y) = start
        do {
            y += 1
            memory[x][y] = nextValue(x, y)
        } while (y < size - 1 && memory[x + 1][y] != 0)
        return Pair(x, y)
    }

    internal open fun nextValue(x: Int, y: Int): Int {
        return (getAllNeighbors(x, y).max() ?: 0) + 1
    }

    internal fun getAllNeighbors(x: Int, y: Int): List<Int> {
        val neighbors = mutableListOf<Int>()
        for (yi in Math.max(0, y - 1)..Math.min(size - 1, y + 1)) {
            for (xi in Math.max(0, x - 1)..Math.min(size - 1, x + 1)) {
                neighbors.add(memory[xi][yi])
            }
        }
        return neighbors
    }

    override fun toString(): String {
        val res = StringBuilder()
        val digits = Math.ceil(Math.log10(getLast().toDouble())).toInt()
        for (y in 0..size - 1) {
            for (x in 0..size - 1) {
                res.append("%${digits}d ".format(memory[x][y]))
            }
            res.append("\n")
        }
        return res.toString()
    }

}

class SecondSpiralMemory(size: Int) : SpiralMemory(size) {

    override fun nextValue(x: Int, y: Int): Int {
        if (x == center && y == center) {
            return 1
        }
        return getAllNeighbors(x, y).sum()
    }

    override fun getSolution(value: Int): Int? {
        var res = Integer.MAX_VALUE
        for (y in 0 until size) {
            for (x in 0 until size) {
                if (memory[x][y] > value) {
                    res = Math.min(res, memory[x][y])
                }
            }
        }
        return res
    }

}