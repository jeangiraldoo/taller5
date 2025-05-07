import Matrices._

val m1 = matrizAlAzar(2, 2)
val m2 = matrizAlAzar(2, 2)


multMatriz(m1, m2)
multMatrizParalela(m1, m2)
multMatrizRec(m1, m2)
sumMatriz(m1, m2)


val m3 = matrizAlAzar(4, 2)
val m4 = matrizAlAzar(4, 2)

multMatriz(m3, m4)
multMatrizParalela(m3, m4)
sumMatriz(m3, m4)


val m5 = matrizAlAzar(16, 2)
val m6 = matrizAlAzar(16, 2)

multMatriz(m5, m6)
multMatrizParalela(m5, m6)
sumMatriz(m5, m6)