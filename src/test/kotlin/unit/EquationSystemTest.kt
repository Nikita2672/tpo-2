package unit

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import system.EquationSystem
import system.LogarithmicFun
import trig.TrigFun
import kotlin.math.PI
import kotlin.math.sqrt

class EquationSystemTest {

    companion object {
        private const val DELTA = 0.01
        private val logarithmicFun = mock(LogarithmicFun::class.java)
        private val trigFun = mock(TrigFun::class.java)
        private lateinit var equationSystem: EquationSystem

        @BeforeAll
        @JvmStatic
        fun setUp() {
            `when`(logarithmicFun.compute(0.5)).thenReturn(-0.92896)
            `when`(logarithmicFun.compute(2.0)).thenReturn(-0.05859)
            `when`(logarithmicFun.compute(3.0)).thenReturn(-0.01014)
            `when`(logarithmicFun.compute(5.0)).thenReturn(0.0)
            `when`(logarithmicFun.compute(100.0)).thenReturn(2.05523)

            `when`(trigFun.compute(-2 * PI)).thenReturn(1.0)
            `when`(trigFun.compute(-1.5 * PI)).thenReturn(0.0)
            `when`(trigFun.compute(-PI)).thenReturn(-1.0)
            `when`(trigFun.compute(-PI / 2)).thenReturn(0.0)
            `when`(trigFun.compute((-3 / 4) * PI)).thenReturn(-sqrt(2.0) / 2)

            equationSystem = EquationSystem(trigFun, logarithmicFun)
        }
    }

    @Test
    fun `test equation system right`() {
        Assertions.assertEquals(-0.92896, equationSystem.compute(0.5), DELTA)
        Assertions.assertEquals(-0.05859, equationSystem.compute(2.0), DELTA)
        Assertions.assertEquals(-0.01014, equationSystem.compute(3.0), DELTA)
        Assertions.assertEquals(0.0, equationSystem.compute(5.0), DELTA)
        Assertions.assertEquals(2.05523, equationSystem.compute(100.0), DELTA)
    }

    @Test
    fun `test equation system left`() {
        Assertions.assertEquals(1.0, equationSystem.compute(-2 * PI), DELTA)
        Assertions.assertEquals(0.0, equationSystem.compute(-1.5 * PI), DELTA)
        Assertions.assertEquals(-1.0, equationSystem.compute(-PI), DELTA)
        Assertions.assertEquals(0.0, equationSystem.compute(-PI / 2), DELTA)
        Assertions.assertEquals(-sqrt(2.0) / 2, equationSystem.compute((-3 / 4) * PI), DELTA)
    }
}