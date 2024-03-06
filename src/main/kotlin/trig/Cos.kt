package trig

import Fun
import kotlin.math.pow
import kotlin.math.sqrt

class Cos(
    private val sin: Sin
): Fun() {


    override fun compute(x: Double): Double {
        return sqrt(1 - sin.compute(x).pow(2))
    }
}