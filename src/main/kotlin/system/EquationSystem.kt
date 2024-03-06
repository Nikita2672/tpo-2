package system

import Fun
import trig.TrigFun

class EquationSystem(
    private val trigFun: TrigFun,
    private val logarithmicFun: LogarithmicFun
) : Fun() {

    override fun compute(x: Double): Double {
        if (x <= 0.0) return trigFun.compute(x)
        return logarithmicFun.compute(x)
    }
}