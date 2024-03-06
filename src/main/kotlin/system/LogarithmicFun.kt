package system

import Fun
import log.Log3
import log.Log5
import kotlin.math.pow

class LogarithmicFun(
    private val log3: Log3,
    private val log5: Log5,
) : Fun() {

    override fun compute(x: Double): Double {
        require(x != 0.0) { "Выход за пределы области допустимых значений" }
        require(x != 1.0) { "Деление на ноль"}
        return (((log5.compute(x).pow(2) - log5.compute(x)) / log3.compute(x)) + (log5.compute(x) - log5.compute(x))).pow(3.0)
    }
}