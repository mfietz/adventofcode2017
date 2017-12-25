import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

internal class FirewallTest {

    private val input = """|0: 3
            |1: 2
            |4: 4
            |6: 4""".trimMargin().split("\n")
    private val firewall = Firewall(input)

    @ParameterizedTest
    @MethodSource("data")
    internal fun checkPosition(depth: Int, time: Int, expected: Int?) {
        assertThat(firewall.position(depth, time), equalTo(expected))
    }

    @Test
    internal fun part1() {
        val input = File("src/stepsPerInsert/13.txt").readLines()
        assertThat(Firewall(input).tripSeverity(), equalTo(1704))
    }

    @Test
    internal fun part2() {
        assertThat(firewall.delay(), equalTo(10))
    }

    companion object {
        @JvmStatic
        fun data() = Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(0, 1, 1),
                Arguments.of(0, 2, 2),
                Arguments.of(0, 3, 1),
                Arguments.of(0, 4, 0),
                Arguments.of(0, 5, 1),
                Arguments.of(1, 0, 0),
                Arguments.of(1, 1, 1),
                Arguments.of(1, 2, 0),
                Arguments.of(1, 3, 1),
                Arguments.of(1, 4, 0),
                Arguments.of(2, 0, null),
                Arguments.of(2, 1, null)
        )
    }
}