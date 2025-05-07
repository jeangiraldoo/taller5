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

  

}
