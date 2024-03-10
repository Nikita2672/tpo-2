package trig

import MathFunction
import kotlin.math.PI

class TrigFun(private val sin: Sin) : MathFunction {

    override fun compute(x: Double): Double {
        return sin.compute(x + (PI / 2))
    }
}