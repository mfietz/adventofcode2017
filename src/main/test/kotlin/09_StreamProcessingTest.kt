import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StreamProcessorTest {

    @ParameterizedTest
    @MethodSource("totalScoreTestData")
    fun totalScore(input: String, expexted: Int) {
        assertThat(StreamProcessor(input).totalScore, equalTo(expexted))
    }

    @ParameterizedTest
    @MethodSource("nonCanceledCharactersWithinGarbageTestData")
    fun nonCanceledCharactersWithinGarbage(input: String, expexted: Int) {
        assertThat(StreamProcessor(input).nonCanceledCharactersWithinGarbage, equalTo(expexted))
    }

    companion object {
        @JvmStatic
        fun totalScoreTestData() = listOf(
                Arguments.of("{}", 1),
                Arguments.of("{{{}}}", 6),
                Arguments.of("{{},{}}", 5),
                Arguments.of("{{{},{},{{}}}}", 16),
                Arguments.of("{<{},{},{{}}>}", 1),
                Arguments.of("{<a>,<a>,<a>,<a>}", 1),
                Arguments.of("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9),
                Arguments.of("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9),
                Arguments.of("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3)
        )

        @JvmStatic
        fun nonCanceledCharactersWithinGarbageTestData() = listOf(
                Arguments.of("<>", 0),
                Arguments.of("<random characters>", 17),
                Arguments.of("<<<<>", 3),
                Arguments.of("<{!>}>", 2),
                Arguments.of("<!!>", 0),
                Arguments.of("<!!!>>", 0),
                Arguments.of("<{o\"i!a,<{i<a>", 10)
        )
    }


}