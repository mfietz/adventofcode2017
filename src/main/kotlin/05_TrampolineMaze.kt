import java.io.File

fun main(args: Array<String>) {
    part1()
    part2()
}

private fun part1() {
    val offsets = File("src/main/resources/05.txt").readLines().map { it.toInt() }.toMutableList()
    var pos = 0
    var steps = 0
    while (0 <= pos && pos < offsets.size) {
        val oldPos = pos;
        pos += offsets[pos]
        offsets[oldPos] += 1
        steps += 1
    }
    println(steps)
}

private fun part2() {
    val offsets = File("src/main/resources/05.txt").readLines().map { it.toInt() }.toMutableList()
    var pos = 0
    var steps = 0
    while (0 <= pos && pos < offsets.size) {
        val oldPos = pos;
        pos += offsets[pos]
        offsets[oldPos] += if(offsets[oldPos] >= 3) -1 else 1
        steps += 1
    }
    println(steps)
}