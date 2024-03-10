package log

import MathFunction

class Log5(private val ln: Ln) : MathFunction {

    override fun compute(x: Double): Double =
        ln.compute(x) / ln.compute(5.0)
}
