import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/07_input.txt").readLines()
    val towers = Towers(input)
    val weight: Map<String, Int> = input.map { it.split(" -> ") }.map {
        val (name, weight) = it[0].split(" ")
        name to weight.replace("\\D".toRegex(), "").toInt()
    }.toMap()

    println(towers.bottomProgram)

    towers.findUnbalanced()
}

class Towers(input: List<String>) {

    val bottomProgram: String?
        get() {
            var bottom: String? = towers.keys.first()
            while (bottom != null) {
                val maybeBottom = towers.entries.firstOrNull { it.value.subtowers != null && it.value.subtowers!!.contains(bottom!!) }?.component1()
                if (maybeBottom == null) return bottom
                bottom = maybeBottom
            }
            return bottom
        }

    private val towers: Map<String, Tower> = input.map {
        val tower = Tower(it)
        tower.name to tower
    }.toMap()

    fun findUnbalanced() {
        towers.forEach { name, tower ->
            if (tower.subtowers != null) {
                val weights = tower.subtowers.map { subtower -> weight(subtower) }
                if (!weights.balanced()) {
                    println(weights)
                }
            }
        }
    }

    private fun weight(name: String): Int {
        val tower = towers.get(name)!!
        if (tower.subtowers == null) {
            return tower.weight
        }
        return tower.weight + tower.subtowers.map { subtower -> weight(subtower) }.sum()
    }

    private fun List<Int>.balanced(): Boolean {
        if (this.size == 1) return true
        val first = this[0]
        this.subList(1, this.size).forEach {
            if (it != first) return false
        }
        return true
    }

}

class Tower(input: String) {
    val name: String
    val weight: Int
    val subtowers: List<String>?

    init {
        val parts = input.split(" -> ")
        name = parts[0].substringBefore(" ")
        weight = parts[0].substring(parts[0].indexOf("(") + 1, parts[0].indexOf(")")).toInt()
        subtowers = if (parts.size > 1) parts[1].split(", ") else null
    }
}
