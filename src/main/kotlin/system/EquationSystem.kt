package system

import MathFunction
import trig.TrigFun

class EquationSystem(
    private val trigFun: TrigFun,
    private val logarithmicFun: LogarithmicFun,
): MathFunction {

    override fun compute(x: Double): Double {
        if (x <= 0.0) return trigFun.compute(x)
        return logarithmicFun.compute(x)
    }
}