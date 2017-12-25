import org.junit.jupiter.api.Test

internal class SporificaVirusTest {

    private val exampleInput =
            """|..#
               |#..
               |...""".trimMargin()

    @Test
    internal fun name() {
        val grid = SporificaVirus(exampleInput)
    }
}