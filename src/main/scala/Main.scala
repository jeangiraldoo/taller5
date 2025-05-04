import Matrices._

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    val A3 = Vector(
      Vector(1, 2, 3),
      Vector(4, 5, 6),
      Vector(7, 8, 9)
    )

    val B3 = Vector(
      Vector(9, 8, 7),
      Vector(6, 5, 4),
      Vector(3, 2, 1)
    )

    println(multMatriz(A3, B3))
  }
}