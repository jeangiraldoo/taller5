import Matrices._

object Main {
  def main(args: Array[String]): Unit = {
    //    println("Hello world!")
    //
    //    val A3 = Vector(
    //      Vector(1, 2, 3),
    //      Vector(4, 5, 6),
    //      Vector(7, 8, 9)
    //    )
    //
    //    val B3 = Vector(
    //      Vector(9, 8, 7),
    //      Vector(6, 5, 4),
    //      Vector(3, 2, 1)
    //    )
    //
    //    println(multMatriz(A3, B3))
    val m1 = matrizAlAzar(8, 2)
    val m2 = matrizAlAzar(8, 2)

    println("matrices:")
    println(m1)
    println(m2)

    println("Resultados")
    println(multMatriz(m1, m2))
    println(multMatrizPar(m1, m2))
    println(multMatrizRec(m1, m2))
    println(multMatrizRecPar(m1, m2))
    println(multStrassen(m1, m2))
    println(multStrassenPar(m1, m2))
  }
}