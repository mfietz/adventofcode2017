import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class CoprocessorTest {

    @Test
    internal fun setToValue() {
        val coprocessor = Coprocessor(listOf("set a 42"))
        assertThat(coprocessor.register("a"), equalTo(42))
    }

    @Test
    internal fun setToRegisterValue() {
        val coprocessor = Coprocessor(listOf("set a 42", "set b a"))
        assertThat(coprocessor.register("b"), equalTo(42))
    }

    @Test
    internal fun decreaseByValue() {
        val coprocessor = Coprocessor(listOf("set a 42", "sub a 17"))
        assertThat(coprocessor.register("a"), equalTo(25))
    }

    @Test
    internal fun decreaseByNegativeValue() {
        val coprocessor = Coprocessor(listOf("set a 42", "sub a -17"))
        assertThat(coprocessor.register("a"), equalTo(59))
    }

    @Test
    internal fun decreaseByRegisterValue() {
        val coprocessor = Coprocessor(listOf("set a 42", "set b 17", "sub a b"))
        assertThat(coprocessor.register("a"), equalTo(25))
    }

    @Test
    internal fun multiplyByValue() {
        val coprocessor = Coprocessor(listOf("set a 42", "mul a 17"))
        assertThat(coprocessor.register("a"), equalTo(714))
    }

    @Test
    internal fun multiplyByRegisterValue() {
        val coprocessor = Coprocessor(listOf("set a 42", "set b 17", "mul a b"))
        assertThat(coprocessor.register("a"), equalTo(714))
    }

    @Test
    internal fun jumpNotZero() {
        val coprocessor = Coprocessor(listOf("set a 42", "jnz a 3"))
        assertThat(coprocessor.index, equalTo(4))
    }

    @Test
    fun notJumpZero() {
        val coprocessor = Coprocessor(listOf("jnz a 3"))
        assertThat(coprocessor.index, equalTo(1))
    }

    @Test
    internal fun part1() {
        val input = "set b 65\nset c b\njnz a 2\njnz 1 5\nmul b 100\nsub b -100000\nset c b\nsub c -17000\nset f 1\n" +
                "set d 2\nset e 2\nset g d\nmul g e\nsub g b\njnz g 2\nset f 0\nsub e -1\nset g e\nsub g b\njnz g -8\n" +
                "sub d -1\nset g d\nsub g b\njnz g -13\njnz f 2\nsub h -1\nset g b\nsub g c\njnz g 2\njnz 1 3\nsub b -17\njnz 1 -23"
        val coprocessor = Coprocessor(input.split('\n'))
        assertThat(coprocessor.mulTimes, equalTo(3969))
    }

    @Test
    internal fun part2() {
        val input = "set b 65\nset c b\njnz a 2\njnz 1 5\nmul b 100\nsub b -100000\nset c b\nsub c -17000\nset f 1\n" +
                "set d 2\nset e 2\nset g d\nmul g e\nsub g b\njnz g 2\nset f 0\nsub e -1\nset g e\nsub g b\njnz g -8\n" +
                "sub d -1\nset g d\nsub g b\njnz g -13\njnz f 2\nsub h -1\nset g b\nsub g c\njnz g 2\njnz 1 3\nsub b -17\njnz 1 -23"
        val coprocessor = Coprocessor(input.split('\n'), a = 1)
    }

}