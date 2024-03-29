import log.Ln
import log.Log3
import log.Log5
import system.EquationSystem
import system.LogarithmicFun
import trig.Sin
import trig.TrigFun
import java.io.FileWriter
import java.io.IOException

fun writeToCsv(
    computeFunction: MathFunction,
    start: Double,
    end: Double,
    step: Double,
    path: String,
    funName: String,
) {
    require(start < end) { "Начало должно быть меньше конца" }
    var x = start
    val data = mutableListOf<Pair<Double, Double>>()
    while (x <= end) {
        val y = computeFunction.compute(x)
        data.add(x to y)
        x += step
    }

    try {
        FileWriter(path).use { writer ->
            writer.append("X,$funName\n")

            data.forEach { (value1, value2) ->
                writer.append("$value1,$value2\n")
            }

            println("Данные успешно записаны в файл $path")
        }
    } catch (e: IOException) {
        println("Ошибка при записи в файл: ${e.message}")
    }
}

fun main() {
    val delta = 0.0001
    val sin = Sin(delta)
    val trigFun = TrigFun(sin)
    val ln = Ln(delta)
    val log3 = Log3(ln)
    val log5 = Log5(ln)
    val logarithmicFun = LogarithmicFun(log3, log5)
    val equationSystem = EquationSystem(trigFun, logarithmicFun)

    writeToCsv(ln, 0.0001, 101.0, 0.1, "src/main/resources/ln.csv", "ln")
    writeToCsv(sin, -8.0, 1.0, 0.1, "src/main/resources/sin.csv", "sin")
    writeToCsv(log3, 0.0001, 101.0, 0.1, "src/main/resources/log3.csv", "log3")
    writeToCsv(log5, 0.0001, 101.0, 0.1, "src/main/resources/log5.csv", "log5")
    writeToCsv(logarithmicFun, 0.0001, 101.0, 0.1, "src/main/resources/logarithmicFun.csv", "logarithmicFun")
    writeToCsv(trigFun, -8.0, 1.0, 0.1, "src/main/resources/trigFun.csv", "trigFun")
    writeToCsv(equationSystem, -8.0, 101.0, 0.1, "src/main/resources/equationSystem.csv", "equationSystem")
}
