import scala.util.Random
import common._

import java.util.concurrent.ForkJoinTask
import scala.collection.parallel.immutable.ParVector

package object Matrices {

  val random = new Random()

  type Matriz = Vector[Vector[Int]]

  def matrizAlAzar(long:Int, vals:Int): Matriz = {
    val v = Vector.fill(long, long){random.nextInt(vals)}
    v
  }

  def vectorAlAzar(long:Int, vals:Int):Vector[Int] = {
    val v = Vector.fill(long){random.nextInt(vals)}
    v
  }

  def transpuesta(m:Matriz): Matriz = {
    val l = m.length
    Vector.tabulate(l,l)((i,j)=>m(j)(i))
  }

  def prodPunto(v1:Vector[Int], v2:Vector[Int]): Int = {
    (v1 zip v2).map({case(i,j) => (i*j)}).sum
  }

  def prodPuntoParD(v1:ParVector[Int],v2:ParVector[Int]):Int = {
    (v1 zip v2).map({case (i,j) => (i*j)}).sum
  }

  // Multiplicación estándar de matrices

  def multMatriz(m1:Matriz, m2:Matriz): Matriz = {
    val m2T = transpuesta(m2)
    val n = m1.length
    Vector.tabulate(n, n)((i, j) => prodPunto(m1(i),m2T(j)))
  }

  def multMatrizPar(m1:Matriz, m2:Matriz): Matriz = {
    val m2T = transpuesta(m2)
    val n = m1.length
    val umbral = 16

    if (n <= umbral) {
      multMatriz(m1, m2)
    } else {
      val tareas: Vector[ForkJoinTask[Vector[Int]]] = Vector.tabulate(n) { i =>
        task {
          Vector.tabulate(n) { j =>
            prodPunto(m1(i), m2T(j))
          }
        }
      }
      tareas.map(_.join())
    }
  }

  // Multiplicación recursiva de matrices

  def subMatriz(m:Matriz, i:Int, j:Int, l:Int):Matriz = {
    Vector.tabulate(l, l)((x, y) => m(i+x)(j+y))
  }
  def sumMatriz(m1: Matriz, m2: Matriz) : Matriz = {
    val tamaño = m1.length
    Vector.tabulate(tamaño, tamaño)((i, j) => m1(i)(j) + m2(i)(j))
  }

  def dividirEnCuadrantes(m: Matriz): Vector[Matriz] = {
    val n = m.length
    val mitad = n/2
    val m11 = subMatriz(m, 0, 0, mitad)
    val m12 = subMatriz(m, 0, mitad, mitad)
    val m21 = subMatriz(m, mitad, 0, mitad)
    val m22 = subMatriz(m, mitad, mitad, mitad)

    Vector(m11, m12, m21, m22)
  }

  def multMatrizRec(m1: Matriz, m2: Matriz): Matriz = {
    val n = m1.length
    val mitad = n/2

    if (n == 1) {
      Vector(Vector(m1(0)(0) * m2(0)(0)))
    } else {
      val cuadrantesM1 = dividirEnCuadrantes(m1)
      val cuadrantesM2 = dividirEnCuadrantes(m2)

      val c11 = sumMatriz(multMatrizRec(cuadrantesM1(0), cuadrantesM2(0)), multMatrizRec(cuadrantesM1(1), cuadrantesM2(2)))
      val c12 = sumMatriz(multMatrizRec(cuadrantesM1(0), cuadrantesM2(1)), multMatrizRec(cuadrantesM1(1), cuadrantesM2(3)))
      val c21 = sumMatriz(multMatrizRec(cuadrantesM1(2), cuadrantesM2(0)), multMatrizRec(cuadrantesM1(3), cuadrantesM2(2)))
      val c22 = sumMatriz(multMatrizRec(cuadrantesM1(2), cuadrantesM2(1)), multMatrizRec(cuadrantesM1(3), cuadrantesM2(3)))

      Vector.tabulate(n, n) { (i, j) =>
        if (i < mitad && j < mitad)        c11(i)(j)
        else if (i < mitad && j >= mitad)  c12(i)(j - mitad)
        else if (i >= mitad && j < mitad)  c21(i - mitad)(j)
        else                               c22(i - mitad)(j - mitad)
      }
    }
  }

  def multMatrizRecPar(m1: Matriz, m2: Matriz) : Matriz = {
    val n = m1.length
    val mitad = n/2
    val umbral = 512

    if (n <= umbral) {
      multMatriz(m1, m2)
    } else {
      val cuadrantesM1 = dividirEnCuadrantes(m1)
      val cuadrantesM2 = dividirEnCuadrantes(m2)

//      val cuadrantesM1 = cuadrantesT1.join()
//      val cuadrantesM2 = cuadrantesT2.join()

      val c11 = (sumMatriz _).tupled(parallel(multMatrizRecPar(cuadrantesM1(0), cuadrantesM2(0)), multMatrizRecPar(cuadrantesM1(1), cuadrantesM2(2))))
      val c12 = (sumMatriz _).tupled(parallel(multMatrizRecPar(cuadrantesM1(0), cuadrantesM2(1)), multMatrizRecPar(cuadrantesM1(1), cuadrantesM2(3))))
      val c21 = (sumMatriz _).tupled(parallel(multMatrizRecPar(cuadrantesM1(2), cuadrantesM2(0)), multMatrizRecPar(cuadrantesM1(3), cuadrantesM2(2))))
      val c22 = (sumMatriz _).tupled(parallel(multMatrizRecPar(cuadrantesM1(2), cuadrantesM2(1)), multMatrizRecPar(cuadrantesM1(3), cuadrantesM2(3))))

      Vector.tabulate(n, n) { (i, j) =>
        if (i < mitad && j < mitad)        c11(i)(j)
        else if (i < mitad && j >= mitad)  c12(i)(j - mitad)
        else if (i >= mitad && j < mitad)  c21(i - mitad)(j)
        else                               c22(i - mitad)(j - mitad)
      }
    }
  }
  def restaMatriz(m1: Matriz, m2: Matriz) : Matriz = {
    val tamaño = m1.length
    Vector.tabulate(tamaño, tamaño)((i, j) => m1(i)(j) - m2(i)(j))
  }
  def multStrassen(m1: Matriz, m2: Matriz) : Matriz = {
    val n = m1.length
    val mitad = n/2
    val a = dividirEnCuadrantes(m1)
    val b = dividirEnCuadrantes(m2)

    val s1 = restaMatriz(b(1), b(3))
    val s2 = sumMatriz(a(0), a(1))
    val s3 = sumMatriz(a(2), a(3))
    val s4 = restaMatriz(b(2), b(0))
    val s5 = sumMatriz(a(0), a(3))
    val s6 = sumMatriz(b(0), b(3))
    val s7 = restaMatriz(a(1), a(3))
    val s8 = sumMatriz(b(2), b(3))
    val s9 = restaMatriz(a(0), a(2))
    val s10 = sumMatriz(b(0), b(1))

    val p1 = multMatriz(a(0), s1)
    val p2 = multMatriz(s2, b(3))
    val p3 = multMatriz(s3, b(0))
    val p4 = multMatriz(a(3), s4)
    val p5 = multMatriz(s5, s6)
    val p6 = multMatriz(s7, s8)
    val p7 = multMatriz(s9, s10)

    val c11 = sumMatriz(restaMatriz(sumMatriz(p5, p4), p2), p6)
    val c12 = sumMatriz(p1, p2)
    val c21 = sumMatriz(p3, p4)
    val c22 = restaMatriz(restaMatriz(sumMatriz(p5, p1), p3), p7)

    Vector.tabulate(n, n) { (i, j) =>
      if (i < mitad && j < mitad)        c11(i)(j)
      else if (i < mitad && j >= mitad)  c12(i)(j - mitad)
      else if (i >= mitad && j < mitad)  c21(i - mitad)(j)
      else                               c22(i - mitad)(j - mitad)
    }
  }

  def multStrassenPar(m1: Matriz, m2: Matriz) : Matriz = {
    val n = m1.length
    val mitad = n / 2
    val umbral = 34

    if (n < umbral) {
      multStrassen(m1, m2)
    } else {
      val a = dividirEnCuadrantes(m1)
      val b = dividirEnCuadrantes(m2)

      val s1 = restaMatriz(b(1), b(3))
      val s2 = sumMatriz(a(0), a(1))
      val s3 = sumMatriz(a(2), a(3))
      val s4 = restaMatriz(b(2), b(0))
      val s5 = sumMatriz(a(0), a(3))
      val s6 = sumMatriz(b(0), b(3))
      val s7 = restaMatriz(a(1), a(3))
      val s8 = sumMatriz(b(2), b(3))
      val s9 = restaMatriz(a(0), a(2))
      val s10 = sumMatriz(b(0), b(1))

      val p1 = task(multMatriz(a(0), s1))
      val p2 = task(multMatriz(s2, b(3)))
      val p3 = task(multMatriz(s3, b(0)))
      val p4 = task(multMatriz(a(3), s4))
      val p5 = task(multMatriz(s5, s6))
      val p6 = task(multMatriz(s7, s8))
      val p7 = task(multMatriz(s9, s10))

      val c11 = sumMatriz(restaMatriz(sumMatriz(p5.join(), p4.join()), p2.join()), p6.join())
      val c12 = sumMatriz(p1.join(), p2.join())
      val c21 = sumMatriz(p3.join(), p4.join())
      val c22 = restaMatriz(restaMatriz(sumMatriz(p5.join(), p1.join()), p3.join()), p7.join())

      Vector.tabulate(n, n) { (i, j) =>
        if (i < mitad && j < mitad) c11(i)(j)
        else if (i < mitad && j >= mitad) c12(i)(j - mitad)
        else if (i >= mitad && j < mitad) c21(i - mitad)(j)
        else c22(i - mitad)(j - mitad)
      }
    }
  }

}
