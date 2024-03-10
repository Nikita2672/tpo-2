package unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import trig.Sin
import trig.TrigFun
import kotlin.math.PI

class TrigFunTest {

    companion object {
        private const val DELTA = 0.01
        private val sin = Mockito.mock(Sin::class.java)
        private lateinit var trigFun: TrigFun

        @BeforeAll
        @JvmStatic
        fun setUp() {
            `when`(sin.compute(PI / 2)).thenReturn(1.0)
            `when`(sin.compute(PI)).thenReturn(0.0)
            `when`(sin.compute(3 * PI / 2)).thenReturn(-1.0)
            `when`(sin.compute(2 * PI)).thenReturn(0.0)
            `when`(sin.compute(5 * PI / 2)).thenReturn(1.0)
            `when`(sin.compute(0.0)).thenReturn(0.0)
            `when`(sin.compute(-PI / 2)).thenReturn(-1.0)
            `when`(sin.compute(Double.NaN)).thenReturn(Double.NaN)
            `when`(sin.compute(Double.POSITIVE_INFINITY)).thenReturn(Double.NaN)
            `when`(sin.compute(Double.NEGATIVE_INFINITY)).thenReturn(Double.NaN)

            `when`(sin.compute(PI / 3 + PI / 2)).thenReturn(0.5)
            `when`(sin.compute(PI / 4 + PI / 2)).thenReturn(0.70711)
            `when`(sin.compute(-PI / 4 + PI / 2)).thenReturn(0.70711)
            `when`(sin.compute(-PI / 3 + PI / 2)).thenReturn(0.5)

            `when`(sin.compute(8 * PI + PI / 2)).thenReturn(1.0)
            `when`(sin.compute(-8 * PI + PI / 2)).thenReturn(1.0)

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