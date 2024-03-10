package integration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import trig.Sin
import trig.TrigFun
import kotlin.math.PI
import java.util.stream.Stream

class TrigFunTest {

    companion object {
        private const val DELTA = 0.01
        private val sin = Sin(DELTA * 0.01)
        private lateinit var trigFun: TrigFun

        @BeforeAll
        @JvmStatic
        fun setUp() {
            trigFun = TrigFun(sin)
        }

        @JvmStatic
        fun provideTestData() =
            listOf(
                Arguments.of(0.0, 1),
                Arguments.of(PI / 2, 0.0),
                Arguments.of(PI, -1.0),
                Arguments.of(3 * PI / 2, 0.0),
                Arguments.of(2 * PI, 1.0),
                Arguments.of(-PI / 2, 0.0),
                Arguments.of(-PI, -1.0),
                Arguments.of(Double.NaN, Double.NaN),
                Arguments.of(Double.POSITIVE_INFINITY, Double.NaN),
                Arguments.of(Double.NEGATIVE_INFINITY, Double.NaN),

                Arguments.of(PI / 3, 0.5),
                Arguments.of(PI / 4, 0.70711),
                Arguments.of(-PI / 4, 0.70711),
                Arguments.of(-PI / 3, 0.5),

                Arguments.of(8 * PI, 1.0),
                Arguments.of(-8 * PI, 1.0),
            )
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun `test TrigFun`(x: Double, expected: Double) {
        assertEquals(expected, trigFun.compute(x), DELTA)
    }
}