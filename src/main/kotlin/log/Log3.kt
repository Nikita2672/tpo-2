package log

import Fun

class Log3(
    private val ln: Ln
): Fun() {

    override fun compute(x: Double): Double {
        return ln.compute(x) / ln.compute(3.0)
    }
}
