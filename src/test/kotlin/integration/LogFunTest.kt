package integration

import log.Log3
import log.Log5
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito
import system.LogarithmicFun

/**
 * @author nivanov
 * @since %CURRENT_VERSION%
 */
class LogFunTest {

    companion object {
        private const val DELTA = 0.01
        private val log3: Log3 = Mockito.mock(Log3::class.java)
        private val log5: Log5 = Mockito.mock(Log5::class.java)
        private lateinit var logarithmicFun: LogarithmicFun

        @BeforeAll
        @JvmStatic
        fun setUp() {

            `when`(log3.compute(0.5)).thenReturn(-0.62910)
            `when`(log3.compute(2.0)).thenReturn(0.63138)
            `when`(log3.compute(3.0)).thenReturn(1.0)
            `when`(log3.compute(5.0)).thenReturn(1.4651)
            `when`(log3.compute(100.0)).thenReturn(4.1918)

            `when`(log5.compute(0.5)).thenReturn(-0.42943)
            `when`(log5.compute(2.0)).thenReturn(0.43098)
            `when`(log5.compute(3.0)).thenReturn(0.6828)
            `when`(log5.compute(5.0)).thenReturn(1.0)
            `when`(log5.compute(100.0)).thenReturn(2.8621)

            logarithmicFun = LogarithmicFun(log3, log5)
        }
    }
    
    @Test
    fun `test logarithm Fun`() {
        Assertions.assertEquals(-0.92896, logarithmicFun.compute(0.5), DELTA)
        Assertions.assertEquals(-0.05859, logarithmicFun.compute(2.0), DELTA)
        Assertions.assertEquals(-0.01014, logarithmicFun.compute(3.0), DELTA)
        Assertions.assertEquals(0.0, logarithmicFun.compute(5.0), DELTA)
        Assertions.assertEquals(2.05523, logarithmicFun.compute(100.0), DELTA)
    }
}