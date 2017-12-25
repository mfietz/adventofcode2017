import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class SpinlockTest {

    @ParameterizedTest
    @MethodSource("insertData")
    internal fun example(inserts: Int, expected: String) {
        val spinlock = Spinlock(inserts, 3)
        assertThat(spinlock.toString(), equalTo(expected))
    }

    @Test
    internal fun part1() {
        val spinlock = Spinlock(2017, 343)
        assertThat(spinlock.valueAfter(2017), equalTo(1914))
    }

    @Test
    internal fun part2() {
        val spinlock = Spinlock2(50_000_000, 343)
        assertThat(spinlock.valueAfterZero, equalTo(41797835))
    }

    companion object {
        @JvmStatic
        fun insertData() = Stream.of(
                Arguments.of(1, "0 (1)"),
                Arguments.of(2, "0 (2) 1"),
                Arguments.of(3, "0  2 (3) 1"),
                Arguments.of(4, "0  2 (4) 3  1"),
                Arguments.of(5, "0 (5) 2  4  3  1"),
                Arguments.of(6, "0  5  2  4  3 (6) 1"),
                Arguments.of(7, "0  5 (7) 2  4  3  6  1"),
                Arguments.of(8, "0  5  7  2  4  3 (8) 6  1"),
                Arguments.of(9, "0 (9) 5  7  2  4  3  8  6  1")
        )
    }

}