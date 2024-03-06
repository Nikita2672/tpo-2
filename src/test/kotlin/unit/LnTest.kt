package unit

import log.Ln
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class LnTest {

    companion object {
        private val arrayTestValues = HashMap<Double, Double>()
        private const val DELTA = 0.001
        private val ln = Ln(DELTA)

        @BeforeAll
        @JvmStatic
        fun setUp() {
            // ключ - x, значение - введенное значение - y
            arrayTestValues[0.0] = Double.NEGATIVE_INFINITY
            arrayTestValues[0.5] = -0.69315
            // пересечение с осью OX
            arrayTestValues[1.0] = 0.0
            arrayTestValues[1.4] = 0.33647223662121295
            arrayTestValues[2.0] = 0.6931471805599453
            // проверка для x>2
            arrayTestValues[3.0] = 1.098612
            arrayTestValues[100.0] = 4.60517
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            arrayTestValues.clear()
        }
    }

    @Test
    fun testLn() {
        for ((key, value) in arrayTestValues) {
            val actual = ln.compute(key)

            println("x = $key actual = $actual expected = $value")
            assertEquals(value, actual, DELTA)
        }
    }
}