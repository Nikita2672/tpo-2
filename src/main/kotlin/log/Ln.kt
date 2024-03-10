package log

import MathFunction
import kotlin.math.abs
import kotlin.math.pow


class Ln(private val eps: Double) : MathFunction {
    init {
        require(eps > 0) { "Epsilon must be positive" }
    }

    private val ln2: Double = compute(2.0)

    override fun compute(x: Double): Double {
        if (x.isNaN()) return Double.NaN
        if (x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY
        require(x >= 0) { "Ln is undefined for x < 0" }
        if (x == 0.0) return Double.NEGATIVE_INFINITY

        // за данными значениями ряд расходится
        if (x > 2) return compute(x / 2.0) + ln2
        var result = 0.0
        var current = 10.0
        var prev = 0.0
        var n = 1
        while (abs(prev - current) >= eps) {
            prev = current
            current = lnTailor(x, n.toDouble())
            result += current
            n++
        }
        return result
    }

    private fun lnTailor(value: Double, n: Double): Double =
        (-1.0).pow(n - 1) * (value - 1).pow(n) / n
}