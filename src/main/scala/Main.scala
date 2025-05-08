import Matrices._
import Benchmark._
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
    val m1 = matrizAlAzar(512, 2)
    val m2 = matrizAlAzar(512, 2)

    val m3 = matrizAlAzar(1024, 2)
    val m4 = matrizAlAzar(1024, 2)

    println(compararAlgoritmos(multMatrizRec, multMatrizRecPar)(m1, m2))


//    println(compararProdPunto(4))
//    println(compararProdPunto(8))
//    println(compararProdPunto(16))
//    println(compararProdPunto(32))
//    println(compararProdPunto(64))
  }
}