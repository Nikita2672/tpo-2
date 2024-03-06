package system

import Fun
import trig.Cos

class EquationSystem(
    private val cos: Cos,
    private val logarithmicFun: LogarithmicFun
) : Fun() {

    override fun compute(x: Double): Double {
        if (x <= 0.0) return cos.compute(x)
        return logarithmicFun.compute(x)
    }
}