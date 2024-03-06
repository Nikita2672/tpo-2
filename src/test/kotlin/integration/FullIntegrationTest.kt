package integration

import log.Ln
import log.Log3
import log.Log5
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import system.EquationSystem
import system.LogarithmicFun
import trig.Sin
import trig.TrigFun
import kotlin.math.PI
import kotlin.math.sqrt

/**
 * @author nivanov
 * @since %CURRENT_VERSION%
 */
class FullIntegrationTest {

    companion object {
        private const val DELTA = 0.01
        private val sin = Sin(DELTA)
        private val trigFun = TrigFun(sin)
        private val ln = Ln(DELTA)
        private val log3 = Log3(ln)
        private val log5 = Log5(ln)
        private val logarithmicFun = LogarithmicFun(log3, log5)
        private val equationSystem = EquationSystem(trigFun, logarithmicFun)
    }

    @Test
    fun `test full integration equation system right`() {
        Assertions.assertEquals(-0.92896, equationSystem.compute(0.5), DELTA)
        Assertions.assertEquals(-0.05859, equationSystem.compute(2.0), DELTA)
        Assertions.assertEquals(-0.01014, equationSystem.compute(3.0), DELTA)
        Assertions.assertEquals(0.0, equationSystem.compute(5.0), DELTA)
        Assertions.assertEquals(2.05523, equationSystem.compute(100.0), DELTA)
    }

    @Test
    fun `test full integration equation system left`() {
        Assertions.assertEquals(1.0, equationSystem.compute(-2 * PI), DELTA)
        Assertions.assertEquals(0.0, equationSystem.compute(-1.5 * PI), DELTA)
        Assertions.assertEquals(-1.0, equationSystem.compute(-PI), DELTA)
        Assertions.assertEquals(0.0, equationSystem.compute(-PI / 2), DELTA)
        Assertions.assertEquals(-sqrt(2.0) / 2, equationSystem.compute((-3.0 / 4.0) * PI), DELTA)
    }
}