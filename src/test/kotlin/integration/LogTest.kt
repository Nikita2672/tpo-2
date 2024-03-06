package integration

import log.Ln
import log.Log3
import log.Log5
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


/**
 * @author nivanov
 * @since %CURRENT_VERSION%
 */
class LogTest {

    companion object {
        private const val DELTA = 0.01
        private val ln = Mockito.mock(Ln::class.java)
        private lateinit var log3: Log3
        private lateinit var log5: Log5

        @BeforeAll
        @JvmStatic
        fun setUp() {

            `when`(ln.compute(0.5)).thenReturn(-0.693147181)
            `when`(ln.compute(1.0)).thenReturn(0.0)
            `when`(ln.compute(2.0)).thenReturn(0.693147)
            `when`(ln.compute(3.0)).thenReturn(1.098612)
            `when`(ln.compute(100.0)).thenReturn(4.60517)

            `when`(ln.compute(5.0)).thenReturn(1.609)

            log3 = Log3(ln)
            log5 = Log5(ln)
        }
    }

    @Test
    fun `test Log3 Fun`() {
        assertEquals(-0.62910, log3.compute(0.5), DELTA)
        assertEquals(0.0, log3.compute(1.0), DELTA)
        assertEquals(0.63138, log3.compute(2.0), DELTA)
        assertEquals(1.0003, log3.compute(3.0), DELTA)
        assertEquals(4.1918, log3.compute(100.0), DELTA)
    }

    @Test
    fun `test Log5 Fun`() {
        assertEquals(-0.42943, log5.compute(0.5), DELTA)
        assertEquals(0.0, log5.compute(1.0), DELTA)
        assertEquals(0.43098, log5.compute(2.0), DELTA)
        assertEquals(0.68281, log5.compute(3.0), DELTA)
        assertEquals(2.8621, log5.compute(100.0), DELTA)
    }
}