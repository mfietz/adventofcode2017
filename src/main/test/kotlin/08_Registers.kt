import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class RegistersTest {

    @Test
    internal fun part1Example() {
        val input = """|b inc 5 if a > 1
            |a inc 1 if b < 5
            |c dec -10 if a >= 1
            |c inc -20 if c == 10""".trimMargin().split("\n")
        assertThat(Registers(input).largestValue, equalTo(1))
    }

    @Test
    internal fun part1() {
        val input = File("src/main/resources/08.txt").readLines()
        assertThat(Registers(input).largestValue, equalTo(6012))
    }

    @Test
    internal fun part2() {
        val input = File("src/main/resources/08.txt").readLines()
        assertThat(Registers(input).largestValueHeld, equalTo(6369))
    }
}