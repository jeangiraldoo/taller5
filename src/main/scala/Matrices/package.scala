import scala.util.Random

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

  def multMatriz(m1:Matriz, m2:Matriz): Matriz = {
    val m2T = transpuesta(m2)
    val n = m1.length
    Vector.tabulate(n, n)((i, j) => prodPunto(m1(i),m2T(j)))
  }

  def subMatriz(m:Matriz, i:Int, j:Int, l:Int):Matriz = {
    Vector.tabulate(l, l)((x, y) => m(i+x)(j+y))
  }

}
