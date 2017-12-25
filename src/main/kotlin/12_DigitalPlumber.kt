import java.io.File

fun main(args: Array<String>) {
    val connections = File("src/stepsPerInsert/12.txt").readLines()
            .map { it.split(" <-> ") }
            .map { Pair(it[0], it[1].split(", ")) }
            .toMap()
    val group =  Neighbors(connections).neighbors("0")
    println(group.size)

    var numGroups = 0
    val assigned = mutableSetOf<String>()
    connections.keys.forEach {
        if(assigned.contains(it)) return@forEach
        numGroups += 1
        val neighbors = Neighbors(connections).neighbors(it)
        assigned += it
        assigned += neighbors
    }
    println(numGroups)
}

class Neighbors(val connections: Map<String, List<String>>) {

    fun neighbors(name: String, res: MutableSet<String> = mutableSetOf()): MutableSet<String> {
        connections.getOrDefault(name, emptyList()).forEach {
            if (res.add(it)) neighbors(it, res)
        }
        return res
    }
}