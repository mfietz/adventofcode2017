import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

class HexEdTest {

    @ParameterizedTest
    @MethodSource("data")
    fun part1Example(input: String, expected: Int) {
        assertThat(HexEd(input).steps(), equalTo(expected))
    }

    @Test
    fun part1() {
        val input = File("src/main/resources/11.txt").readText()
        assertThat(HexEd(input).steps(), equalTo(687))
    }

    @Test
    internal fun part2() {
        val steps = File("src/main/resources/11.txt").readText().split(",")
        val furthest = (1..steps.size).map {
            val input = steps.subList(0, it).joinToString(",")
            HexEd(input).steps()
        }.max()
        assertThat(furthest, equalTo(1483))
    }

    companion object {
        @JvmStatic
        fun data() = Stream.of(
                Arguments.of("ne,ne,ne", 3),
                Arguments.of("ne,ne,sw,sw", 0),
                Arguments.of("ne,ne,s,s", 2),
                Arguments.of("ne,s,nw", 0),
                Arguments.of("se,sw,se,sw,sw", 3)
        )
    }
}