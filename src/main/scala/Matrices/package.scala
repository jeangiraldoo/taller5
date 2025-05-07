import scala.util.Random
import common._

import java.util.concurrent.ForkJoinTask

package object Matrices {

  val random = new Random()

  type Matriz = Vector[Vector[Int]]

  def matrizAlAzar(long:Int, vals:Int): Matriz = {
    val v = Vector.fill(long, long){random.nextInt(vals)}
    v
  }

  def transpuesta(m:Matriz): Matriz = {
    val l = m.length
    Vector.tabulate(l,l)((i,j)=>m(j)(i))
  }

  def prodPunto(v1:Vector[Int], v2:Vector[Int]): Int = {
    (v1 zip v2).map({case(i,j) => (i*j)}).sum
  }

  // Multiplicación estándar de matrices

  def multMatriz(m1:Matriz, m2:Matriz): Matriz = {
    val m2T = transpuesta(m2)
    val n = m1.length
    Vector.tabulate(n, n)((i, j) => prodPunto(m1(i),m2T(j)))
  }

  def multMatrizParalela(m1:Matriz, m2:Matriz): Matriz = {
    val m2T = transpuesta(m2)
    val n = m1.length

    val tareas: Vector[ForkJoinTask[Vector[Int]]] = Vector.tabulate(n) { i =>
      task {
        Vector.tabulate(n) { j =>
          prodPunto(m1(i), m2T(j))
        }
      }
    }
    tareas.map(_.join())
  }

  // Multiplicación recursiva de matrices

  def subMatriz(m:Matriz, i:Int, j:Int, l:Int):Matriz = {
    Vector.tabulate(l, l)((x, y) => m(i+x)(j+y))
  }
  def sumMatriz(m1: Matriz, m2: Matriz) : Matriz = {
    val tamaño = m1.length
    Vector.tabulate(tamaño, tamaño)((i, j) => m1(i)(j) + m2(i)(j))
  }

  def multMatrizRec(m1: Matriz, m2: Matriz): Matriz = {
    val n = m1.length
    val mitad = n/2

    def dividirEnCuadrantes(m: Matriz): Vector[Matriz] = {
      val m11 = subMatriz(m, 0, 0, mitad)
      val m12 = subMatriz(m, 0, mitad, mitad)
      val m21 = subMatriz(m, mitad, 0, mitad)
      val m22 = subMatriz(m, mitad, mitad, mitad)
      Vector(m11, m12, m21, m22)
    }

    if (n == 1) {
      Vector(Vector(m1(0)(0) * m2(0)(0)))
    } else {
      val cuadrantesM1 = dividirEnCuadrantes(m1)
      val cuadrantesM2 = dividirEnCuadrantes(m2)
      val c11 = sumMatriz(multMatrizRec(cuadrantesM1(0), cuadrantesM2(0)), multMatrizRec(cuadrantesM1(1), cuadrantesM2(2)))
      val c12 = sumMatriz(multMatrizRec(cuadrantesM1(0), cuadrantesM2(1)), multMatrizRec(cuadrantesM1(1), cuadrantesM2(3)))
      val c21 = sumMatriz(multMatrizRec(cuadrantesM1(2), cuadrantesM2(0)), multMatrizRec(cuadrantesM1(3), cuadrantesM2(1)))
      val c22 = sumMatriz(multMatrizRec(cuadrantesM1(2), cuadrantesM2(1)), multMatrizRec(cuadrantesM1(3), cuadrantesM2(3)))

      Vector.tabulate(n, n) { (i, j) =>
        if (i < mitad && j < mitad)        c11(i)(j)
        else if (i < mitad && j >= mitad)  c12(i)(j - mitad)
        else if (i >= mitad && j < mitad)  c21(i - mitad)(j)
        else                               c22(i - mitad)(j - mitad)
      }
    }
  }

}
