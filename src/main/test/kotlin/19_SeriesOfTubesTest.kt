import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

internal class SeriesOfTubesTest {

    private val input =
            """>     |
               >     |  +--+
               >     A  |  C
               > F---|----E|--+
               >     |  |  |  D
               >     +B-+  +--+"""
                    .trimMargin(">")
                    .split('\n')

    @Test
    internal fun start() {
        assertThat(SeriesOfTubes(input).currentPos, equalTo(Pos(5, 0)))
    }

    @Test
    internal fun firstStep() {
        val tubes = SeriesOfTubes(input)
        tubes.next()
        assertThat(tubes.currentPos, equalTo(Pos(5, 1)))
    }

    @Test
    internal fun secondStep() {
        val tubes = SeriesOfTubes(input)
        tubes.next()
        tubes.next()
        assertThat(tubes.currentPos, equalTo(Pos(5, 2)))
    }

    @Test
    internal fun path() {
        assertThat(SeriesOfTubes(input).path(), equalTo("ABCDEF"))
    }

}