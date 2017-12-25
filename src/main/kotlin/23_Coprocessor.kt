class Coprocessor(instructions: List<String>, a: Int = 0) {

    private val registers = mutableMapOf<String, Int>()

    var index = 0
    var mulTimes = 0

    init {
        registers["a"] = a
        while(0 <= index && index < instructions.size) {
            val instruction = instructions[index]
            val (command, target, source) = instruction.split(' ')
            when (command) {
                "set" -> set(target, source)
                "sub" -> sub(target, source)
                "mul" -> mul(target, source)
                "jnz" -> jumpNotZero(target, source)
                else -> throw IllegalArgumentException()
            }
            println(registers)
        }
    }

    fun register(name: String): Int {
        return registers.getOrDefault(name, 0)
    }

    private fun set(target: String, source: String) {
        val value = source.toIntOrNull() ?: register(source)
        registers[target] = value
        index += 1
    }

    private fun sub(target: String, source: String) {
        val targetValue = register(target)
        val value = source.toIntOrNull() ?: register(source)
        registers[target] = targetValue - value
        index += 1
    }

    private fun mul(target: String, source: String) {
        mulTimes += 1
        val targetValue = register(target)
        val value = source.toIntOrNull() ?: register(source)
        registers[target] = targetValue * value
        index += 1
    }

    private fun jumpNotZero(condition: String, source: String) {
        val conditionValue = condition.toIntOrNull() ?: register(condition)
        if(conditionValue == 0) {
            index += 1
            return
        }
        val offset = source.toIntOrNull() ?: register(source)
        index += offset
    }

}
