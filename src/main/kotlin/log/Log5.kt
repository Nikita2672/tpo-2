package log

import Fun

class Log5(
    private val ln: Ln
): Fun() {

    override fun compute(x: Double): Double {
        return ln.compute(x) / ln.compute(5.0)
    }
}
