import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

internal class DiscFragmentationTest {

    private val exampleDisc = Disc("flqrgnkx")
    private val inputDisc = Disc("ffayrhll")

    @Test
    internal fun part1Example() {
        assertThat(exampleDisc.usedSquares, equalTo(8108))
    }

    @Test
    internal fun part1() {
        assertThat(inputDisc.usedSquares, equalTo(8190))
    }

    @Test
    internal fun part2Example() {
        assertThat(exampleDisc.regions, equalTo(1242))
    }

    @Test
    internal fun part2() {
        assertThat(inputDisc.regions, equalTo(1134))
    }

}
