class Spinlock(inserts: Int, val stepsPerInsert: Int) {

    private val buffer = mutableListOf(0)
    private var step = 0
    private var position = 0

    init {
        (1..inserts).forEach { insertNext() }
    }

    fun insertNext() {
        step += 1
        position = ((position + stepsPerInsert) % buffer.size) + 1
        buffer.add(position, step)
    }

    fun valueAfter(value: Int): Int {
        return buffer[buffer.indexOf(value) + 1]
    }

    override fun toString(): String {
        val res = StringBuilder()

        var index = -1
        buffer.forEach { value ->
            index += 1
            if (index == position) {
                res.append("($value)")
            } else {
                res.append(" $value ")
            }
        }
        return res.toString().trim()
    }

}

class Spinlock2(inserts: Int, val stepsPerInsert: Int) {

    private var bufferSize = 1
    private var position = 0

    var valueAfterZero: Int? = null

    init {
        (1..inserts).forEach { insertNext() }
    }

    fun insertNext() {
        position = ((position + stepsPerInsert) % bufferSize) + 1
        if(position == 1) valueAfterZero = bufferSize
        bufferSize += 1
    }

}