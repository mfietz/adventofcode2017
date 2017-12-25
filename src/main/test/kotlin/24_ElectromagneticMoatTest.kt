import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

internal class ElectromagneticMoatTest {

    private val exampleStrongestBridge = listOf(Component(0, 1), Component(10, 1), Component(9, 10))

    @Test
    internal fun strength() {
        val bridge = exampleStrongestBridge
        assertThat(ElectromagneticMoat.strengthOf(bridge), equalTo(31))
    }

    @Test
    internal fun strongestBridge() {
        val input = "0/2\n2/2\n2/3\n3/4\n3/5\n0/1\n10/1\n9/10".split('\n')
        assertThat(ElectromagneticMoat(input).strongestBridge(), equalTo(exampleStrongestBridge))
    }

}