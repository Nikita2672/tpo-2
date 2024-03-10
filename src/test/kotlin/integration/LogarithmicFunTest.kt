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
import system.LogarithmicFun

class LogarithmicFunTest {

    companion object {
        private const val DELTA = 0.01
        private val ln = Ln(DELTA * 0.01)
        private lateinit var log3: Log3
        private lateinit var log5: Log5
        private lateinit var logarithmicFun: LogarithmicFun

        @BeforeAll
        @JvmStatic
        fun setUp() {
            log3 = Log3(ln)
            log5 = Log5(ln)
            logarithmicFun = LogarithmicFun(log3, log5)
        }

        @JvmStatic
        fun provideTestData() =
            listOf(
                Arguments.of(0.5, -0.92896),
                Arguments.of(2.0, -0.05859),
                Arguments.of(3.0, -0.01014),
                Arguments.of(5.0, 0.0),
                Arguments.of(100.0, 2.05523)
            )
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun `test logarithm Fun`(x: Double, expected: Double) {
        assertEquals(expected, logarithmicFun.compute(x), DELTA)
    }

    @Test
    fun `test logarithm Fun throws IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) {
            logarithmicFun.compute(1.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            logarithmicFun.compute(-1.0)
        }
    }
}