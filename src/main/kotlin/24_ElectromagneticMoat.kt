class ElectromagneticMoat(input: List<String>) {

    private val components: List<Component>

    companion object {
        fun strengthOf(bridge: List<Component>): Int = bridge.map { it.pin1 + it.pin2 }.sum()
    }

    init {
        components = input.map {
            Component(it.substringBefore('/').toInt(), it.substringAfter('/').toInt())
        }
    }

    fun strongestBridge(): List<Component>? {
        val starters = components.filter { it.pin1 == 0 || it.pin2 == 0 }
                .map { if (it.pin1 == 0) it else it.turn() }
        val bridgeStrength = starters.map { expanddBridge(listOf(it)) }.flatten().associateBy { strengthOf(it) }
        return bridgeStrength[bridgeStrength.keys.max()]
    }

    fun expanddBridge(current: List<Component>): List<List<Component>> {
        val pinToMatch = current.last().pin2
        val nextPins = components.map { if(!current.contains(it) && !current.contains(it.turn())) it else null }
                .filterNotNull()
                .map { if(it.pin1 == pinToMatch) it else if (it.pin2 == pinToMatch) it.turn() else null}
                .filterNotNull()
        if(nextPins.isEmpty()) return current
        return listOf()
    }


}

data class Component(val pin1: Int, val pin2: Int) {

    fun turn() = Component(this.pin2, this.pin1)

}