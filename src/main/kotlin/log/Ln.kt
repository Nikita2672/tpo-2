package log

import Fun
import java.io.FileWriter
import java.io.IOException
import kotlin.math.abs
import kotlin.math.pow


class Ln(eps: Double) : Fun() {
    private val eps: Double
    private val ln2: Double

    init {
        funcName = "ln(x)"
        this.eps = eps * 0.1
        ln2 = compute(2.0)
    }

    private fun lnTailor(value: Double, n: Double): Double {
        return (-1.0).pow(n - 1) * (value - 1).pow(n) / n
    }

    override fun compute(x: Double): Double {
        require(!(eps < 0 || x < 0)) { "Точность должна быть положительной" }
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
}