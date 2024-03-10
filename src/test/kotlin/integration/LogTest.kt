package integration

import log.Ln
import log.Log3
import log.Log5
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LogTest {

    companion object {
        private const val DELTA = 0.01
        private val ln = Ln(DELTA * 0.01)
        private lateinit var log3: Log3
        private lateinit var log5: Log5

        @BeforeAll
        @JvmStatic
        fun setUp() {
            log3 = Log3(ln)
            log5 = Log5(ln)
        }

        @JvmStatic
        fun provideLog3TestData() =
            listOf(
                Arguments.of(0.5, -0.62910),
                Arguments.of(1.0, 0.0),
                Arguments.of(2.0, 0.63138),
                Arguments.of(3.0, 1.0),
                Arguments.of(100.0, 4.1918),
                Arguments.of(Double.NaN, Double.NaN),
                Arguments.of(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
            )

        @JvmStatic
        fun provideLog5TestData() =
            listOf(
                Arguments.of(0.5, -0.42943),
                Arguments.of(1.0, 0.0),
                Arguments.of(2.0, 0.43098),
                Arguments.of(3.0, 0.68281),
                Arguments.of(100.0, 2.8621),
                Arguments.of(Double.NaN, Double.NaN),
                Arguments.of(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
            )

    }

    @ParameterizedTest
    @MethodSource("provideLog3TestData")
    fun `test Log3 Fun`(x: Double, expected: Double) {
        assertEquals(expected, log3.compute(x), DELTA)
    }

    @ParameterizedTest
    @MethodSource("provideLog5TestData")
    fun `test Log5 Fun`(x: Double, expected: Double) {
        assertEquals(expected, log5.compute(x), DELTA)
    }

    @Test
    fun `test Log3 Fun throws IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) {
            log3.compute(-1.0)
        }
    }

    @Test
    fun `test Log5 Fun throws IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) {
            log5.compute(-1.0)
        }
    }
}