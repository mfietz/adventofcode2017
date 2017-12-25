import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

internal class DanceTest {

    @Test
    internal fun spin() {
        val dance = Dance("abcde", listOf("s1"))
        dance.dance()
        assertThat(dance.orderAsString(), equalTo("eabcd"))
    }

    @Test
    internal fun exchange() {
        val dance = Dance("eabcd", listOf("x3/4"))
        dance.dance()
        assertThat(dance.orderAsString(), equalTo("eabdc"))
    }

    @Test
    internal fun swap() {
        val dance = Dance("eabdc", listOf("pe/b"))
        dance.dance()
        assertThat(dance.orderAsString(), equalTo("baedc"))
    }

}