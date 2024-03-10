package unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import trig.Sin
import java.lang.Double.NaN
import kotlin.math.PI
import kotlin.math.sqrt

class SinTest {

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun testSin(x: Double, expected: Double) {
        val actual = sin.compute(x)
        assertEquals(expected, actual, DELTA)
    }

    companion object {
        private const val DELTA = 0.00001
        private val sin = Sin(DELTA)

        @JvmStatic
        fun provideTestData() =
            mapOf(
                // проверка нулевого значения
                0.0 to 0.0,
                // проверка правой стороны
                PI / 6 to 0.5,
                PI / 4 to sqrt(2.0) / 2,
                PI / 3 to sqrt(3.0) / 2,
                PI / 2 to 1.0,
                2 * PI / 3 to sqrt(3.0) / 2,
                3 * PI / 4 to sqrt(2.0) / 2,
                5 * PI / 6 to 0.5,
                // проверка левой стороны (на четность)
                -PI / 6 to -0.5,
                -PI / 4 to -sqrt(2.0) / 2,
                -PI / 3 to -sqrt(3.0) / 2,
                -PI / 2 to -1.0,
                -2 * PI / 3 to -sqrt(3.0) / 2,
                -3 * PI / 4 to -sqrt(2.0) / 2,
                -5 * PI / 6 to -0.5,
                // проверка граничных значений
                -PI to 0.0,
                PI to 0.0,
                // тестирование NaN, Infinity
                NaN to NaN,
                Double.POSITIVE_INFINITY to NaN,
                Double.NEGATIVE_INFINITY to NaN,
                // тестовые значения за границами -2pi ; 2pi
                7 * PI / 6 to -0.5,
                -7 * PI / 6 to 0.5
            )
                .map { Arguments.of(it.key, it.value) }
    }
}