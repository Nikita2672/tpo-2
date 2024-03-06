package unit

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import trig.Sin
import java.lang.Double.NaN
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.math.PI
import kotlin.math.sqrt


class SinTest {

    companion object {


        private val arrayTestValues = HashMap<Double, Double>()
        private val DELTA = 0.00001
        private val sin = Sin(DELTA)

        @BeforeAll
        @JvmStatic
        fun setUp() {
            // ключ - x, значение - введенное значение - y

            //проверка нулевого значения
            arrayTestValues[0.0] = 0.0

            //проверка правой стороны
            arrayTestValues[PI / 6] = 0.5
            arrayTestValues[PI / 4] = sqrt(2.0) / 2
            arrayTestValues[PI / 3] = sqrt(3.0) / 2
            arrayTestValues[PI / 2] = 1.0
            arrayTestValues[2 * PI / 3] = sqrt(3.0) / 2
            arrayTestValues[3 * PI / 4] = sqrt(2.0) / 2
            arrayTestValues[5 * PI / 6] = 0.5

            //проверка левой стороны (на четность)
            arrayTestValues[-PI / 6] = -0.5
            arrayTestValues[-PI / 4] = -sqrt(2.0) / 2
            arrayTestValues[-PI / 3] = -sqrt(3.0) / 2
            arrayTestValues[-PI / 2] = -1.0
            arrayTestValues[-2 * PI / 3] = -sqrt(3.0) / 2
            arrayTestValues[-3 * PI / 4] = -sqrt(2.0) / 2
            arrayTestValues[-5 * PI / 6] = -0.5

            //проверка граничных значений
            arrayTestValues[-PI] = 0.0
            arrayTestValues[PI] = 0.0

            //тестирование NaN, Infinity
            arrayTestValues[NaN] = NaN
            arrayTestValues[Double.POSITIVE_INFINITY] = NaN
            arrayTestValues[Double.NEGATIVE_INFINITY] = NaN

            //тестовые значения за границами -2pi ; 2pi
            arrayTestValues[7 * PI / 6] = -0.5
            arrayTestValues[-7 * PI / 6] = 0.5
        }

        @AfterAll
        fun tearDown() {
            arrayTestValues.clear()
        }
    }

    @Test
    fun testSin() {
        for ((key, value) in arrayTestValues) {
            val actual = sin.compute(key)

            println("x = $key actual = $actual expected = $value")
            assertEquals(value, actual, DELTA)
        }
    }
}