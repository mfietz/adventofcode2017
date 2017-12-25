import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class KnotHashTest {

    @Test
    fun part1() {
        assertThat(KnotHash(listOf(130, 126, 1, 11, 140, 2, 255, 207, 18, 254, 246, 164, 29, 104, 0, 224)).part1, equalTo(38628))
    }

    @Test
    fun knotHash() {
        assertThat(KnotHash("").hash, equalTo("a2582a3a0e66e6e86e3812dcb672a272"))
        assertThat(KnotHash("AoC 2017").hash, equalTo("33efeb34ea91902bb2f59c9920caa6cd"))
        assertThat(KnotHash("1,2,3").hash, equalTo("3efbe78a8d82f29979031a4aa0b16a9d"))
        assertThat(KnotHash("1,2,4").hash, equalTo("63960835bcdc130f0b66d7ff4f6a5a8e"))
    }

    @Test
    fun xor() {
        val input = intArrayOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)
        val result = Xor(input).result
        assertThat(result, equalTo(64))
    }

    @Test
    fun hex() {
        assertThat(Hex(64).toString(), equalTo("40"))
        assertThat(Hex(7).toString(), equalTo("07"))
        assertThat(Hex(255).toString(), equalTo("ff"))
    }

}