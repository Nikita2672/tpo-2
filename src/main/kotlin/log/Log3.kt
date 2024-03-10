package log

import MathFunction

class Log3(private val ln: Ln) : MathFunction {

    override fun compute(x: Double): Double =
        ln.compute(x) / ln.compute(3.0)
}
