package trig

import MathFunction
import java.lang.Math.PI
import kotlin.math.abs
import kotlin.math.pow


class Sin(private val eps: Double) : MathFunction {

    init {
        require(eps > 0) { "Точность должна быть положительным числом" }
    }

    private fun sinTailor(value: Double, n: Int): Double {
        return (-1.0).pow(n) * value.pow(2 * n + 1) / factorial((2 * n + 1).toLong())
    }

    override fun compute(x: Double): Double {
        if (x.isNaN() || x.isInfinite()) {
            return Double.NaN
        }
        val xShortened = shortenRange(x)
        require(eps > 0) { "Точность должна быть положительным числом" }
        var result = 0.0
        var current = 10.0
        var prev = 0.0
        var n = 0
        while (abs(prev - current) >= eps) {
            prev = current
            current = sinTailor(xShortened, n)
            result += current
            n++
        }
        return result
    }

    private fun factorial(value: Long): Long {
        var result = 1L
        for (i in 1..value) {
            result *= i
        }
        return result
    }

    private fun shortenRange(x: Double): Double {
        return if (x > PI || x < -PI) {
            val k: Double = x % (2 * PI)
            if (k < -PI) {
                return k + 2 * PI
            }
            if (k > PI) {
                k - 2 * PI
            } else k
        } else {
            x
        }
    }
}