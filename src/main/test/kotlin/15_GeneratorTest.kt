import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class GeneratorTest {

    @Test
    internal fun generatorA() {
        val generator = Generator(16807, 65)
        assertThat(generator.next(), equalTo(1092455L))
        assertThat(generator.next(), equalTo(1181022009L))
        assertThat(generator.next(), equalTo(245556042L))
        assertThat(generator.next(), equalTo(1744312007L))
        assertThat(generator.next(), equalTo(1352636452L))
    }

    @Test
    internal fun generatorB() {
        val generator = Generator(48271, 8921)
        assertThat(generator.next(), equalTo(430625591L))
        assertThat(generator.next(), equalTo(1233683848L))
        assertThat(generator.next(), equalTo(1431495498L))
        assertThat(generator.next(), equalTo(137874439L))
        assertThat(generator.next(), equalTo(285222916L))
    }

    @ParameterizedTest
    @MethodSource("binary")
    internal fun binary(value: Long, expected: String) {
        assertThat(Binary(value).toString(), equalTo(expected))
    }

    @Test
    internal fun lowest16BitMatch() {
        assertThat("lowest 16 bit should match", Binary(245556042).lowest16BitMatchWith(Binary(1431495498)))
        assertThat("lowest 16 bit should not match", !Binary(1092455).lowest16BitMatchWith(Binary(430625591)))
    }

    @Test
    internal fun finalCounts1() {
        val count = JudgeFinalCount(Generator(16807, 65), Generator(48271, 8921))
        assertThat(count.finalCount(40_000_000), equalTo(588))
    }

    @Test
    internal fun finalCounts2() {
        val count = JudgeFinalCount(Generator(16807, 65, 4),
                Generator(48271, 8921, 8))
        assertThat(count.finalCount(5_000_000), equalTo(309))
    }

    companion object {
        @JvmStatic
        fun binary() = Stream.of(
                Arguments.of(1092455L, "00000000000100001010101101100111"),
                Arguments.of(430625591L, "00011001101010101101001100110111"),
                Arguments.of(1181022009L, "01000110011001001111011100111001"),
                Arguments.of(1233683848L, "01001001100010001000010110001000"),
                Arguments.of(245556042L, "00001110101000101110001101001010"),
                Arguments.of(1744312007L, "01100111111110000001011011000111"),
                Arguments.of(1431495498L, "01010101010100101110001101001010"),
                Arguments.of(137874439L, "00001000001101111100110000000111"),
                Arguments.of(1352636452L, "01010000100111111001100000100100"),
                Arguments.of(285222916L, "00010001000000000010100000000100")
        )

    }


}