class SporificaVirus(input: String) {



}

enum class Direction() {
    UP, DOWN, LEFT, RIGHT;

    fun turnLeft()  = when(this) {
        UP -> LEFT
        LEFT -> DOWN
        DOWN -> RIGHT
        RIGHT -> UP
    }

    fun turnRight()  = when(this) {
        UP -> RIGHT
        LEFT -> UP
        DOWN -> LEFT
        RIGHT -> DOWN
    }
}
