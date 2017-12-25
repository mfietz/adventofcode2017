import java.io.File

class Registers(val instructions: List<String>) {

    val largestValue: Int?
        get() = registers.values.max()

    var largestValueHeld = Integer.MIN_VALUE

    private val registers = mutableMapOf<String, Int>()

    init {
        instructions.forEach {
            val (action, condition) = it.split(" if ")
            if (!condition.succeeds()) {
                return@forEach
            }
            val (registerName, operator, value) = action.split(" ")
            val registerValue = registers[registerName] ?: 0
            val newValue: Int
            if (operator == "inc") {
                newValue = registerValue + value.toInt()
            } else {
                newValue = registerValue - value.toInt()
            }
            registers[registerName] = newValue
            largestValueHeld = Math.max(largestValueHeld, newValue)
        }
    }

    private fun String.succeeds(): Boolean {
        val (registerName, operator, value) = this.split(" ")
        val registerValue = registers[registerName] ?: 0
        return when (operator) {
            ">" -> registerValue > value.toInt()
            "<" -> registerValue < value.toInt()
            ">=" -> registerValue >= value.toInt()
            "<=" -> registerValue <= value.toInt()
            "==" -> registerValue == value.toInt()
            "!=" -> registerValue != value.toInt()
            else -> throw RuntimeException("Unknown operator")
        }

    }

}


