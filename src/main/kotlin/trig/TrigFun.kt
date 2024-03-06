package trig

import Fun
import kotlin.math.PI

class TrigFun(sin: Sin): Fun() {

    private val sin: Sin

    init {
        funcName = "cos(x)"
        this.sin = sin
    }

    override fun compute(x: Double): Double {
        return sin.compute(x + (PI / 2))
    }

}