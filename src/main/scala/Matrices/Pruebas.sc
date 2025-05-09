import Matrices._

val m1 = matrizAlAzar(1024, 2)
val m2 = matrizAlAzar(1024, 2)


multMatriz(m1, m2)
multMatrizPar(m1, m2)
multMatrizRec(m1, m2)
multMatrizRecPar(m1, m2)

sumMatriz(m1, m2)
restaMatriz(m1, m2)


val m3 = matrizAlAzar(4, 2)
val m4 = matrizAlAzar(4, 2)

multMatriz(m3, m4)
multMatrizPar(m3, m4)
multMatrizRec(m3, m4)
multMatrizRecPar(m3, m4)

sumMatriz(m3, m4)
restaMatriz(m3, m4)


val m5 = matrizAlAzar(16, 2)
val m6 = matrizAlAzar(16, 2)

multMatriz(m5, m6)
multMatrizPar(m5, m6)
multMatrizRec(m5, m6)
multMatrizRecPar(m5, m6)

sumMatriz(m5, m6)
restaMatriz(m5, m6)

//test

//multMatriz

val m7 = Vector(Vector(1, 2), Vector(3, 4))
val m8 = Vector(Vector(5, 6), Vector(7, 8))
multMatriz(m7, m8) //Vector(Vector(19, 22), Vector(43, 50))

val m9 = Vector(Vector(1, 0), Vector(0, 1))
val m10 = Vector(Vector(2, 3), Vector(4, 5))
multMatriz(m9, m10) // Vector(Vector(2, 3), Vector(4, 5))

val m13 = Vector(Vector(0, 0), Vector(0, 0))
val m14 = Vector(Vector(-1, 2), Vector(3, -4))
multMatriz(m13, m14) // Vector(Vector(0, 0), Vector(0, 0))

val m15 = Vector(Vector(-1, 2), Vector(3, -4))
val m16 = Vector(Vector(-5, 6), Vector(7, -8))
multMatriz(m15, m16) // Vector(Vector(19, -22), Vector(-43, 50))

val m17 = Vector(Vector(1, 2, 4, 8), Vector(16, 32, 64, 128), Vector(1, 1, 1, 1), Vector(2, 2, 2, 2))
val m18 = Vector(Vector(8, 4, 2, 1), Vector(1, 2, 4, 8), Vector(2, 1, 8, 4), Vector(4, 8, 1, 2))
multMatriz(m17, m18) //Vector(Vector(50, 76, 50, 49),Vector(800, 1216, 800, 784),Vector(15, 15, 15, 15),Vector(30, 30, 30, 30))

val m19 = Vector(Vector(1, 0, 0, 0), Vector(0, 2, 0, 0), Vector(0, 0, 3, 0), Vector(0, 0, 0, 4))
val m20 = Vector(Vector(5, 6, 7, 8), Vector(9, 10, 11, 12), Vector(13, 14, 15, 16), Vector(17, 18, 19, 20))
multMatriz(m19, m20) // Vector(Vector(5, 6, 7, 8), Vector(18, 20, 22, 24), Vector(39, 42, 45, 48), Vector(68, 72, 76, 80))

// submatriz

val m21 = Vector(Vector(1, 2), Vector(3, 4))
subMatriz(m19, 0, 0, 1) // Vector(Vector(1))
subMatriz(m19, 0, 1, 1) // Vector(Vector(2))
subMatriz(m19, 1, 0, 1) // Vector(Vector(3))
subMatriz(m19, 1, 1, 1) // Vector(Vector(4))
subMatriz(m19, 0, 0, 2) // Vector(Vector(1, 2), Vector(3, 4))

val m22 = Vector(Vector(1, 2, 3, 4), Vector(5, 6, 7, 8), Vector(9, 10, 11, 12), Vector(13, 14, 15, 16))
subMatriz(m20, 0, 0, 2) // Vector(Vector(1, 2), Vector(5, 6))
subMatriz(m20, 0, 2, 2) // Vector(Vector(3, 4), Vector(7, 8))
subMatriz(m20, 2, 0, 2) // Vector(Vector(9, 10), Vector(13, 14))
subMatriz(m20, 2, 2, 2) // Vector(Vector(11, 12), Vector(15, 16))
subMatriz(m20, 1, 1, 2) // Vector(Vector(6, 7), Vector(10, 11))

val m23 = Vector(Vector(1, 2, 3, 4), Vector(5, 6, 7, 8), Vector(9, 10, 11, 12), Vector(13, 14, 15, 16))
subMatriz(m23, 1, 1, 2) // Vector(Vector(6, 7), Vector(10, 11))

val m24 = Vector(Vector(1, 2, 3, 4, 5, 6, 7, 8), Vector(9, 10, 11, 12, 13, 14, 15, 16), Vector(17, 18, 19, 20, 21, 22, 23, 24), Vector(25, 26, 27, 28, 29, 30, 31, 32), Vector(33, 34, 35, 36, 37, 38, 39, 40), Vector(41, 42, 43, 44, 45, 46, 47, 48), Vector(49, 50, 51, 52, 53, 54, 55, 56), Vector(57, 58, 59, 60, 61, 62, 63, 64))
subMatriz(m24, 2, 2, 4) // Vector(Vector(19, 20, 21, 22), Vector(27, 28, 29, 30), Vector(35, 36, 37, 38), Vector(43, 44, 45, 46))

val m25 = Vector(Vector(2, 4), Vector(8, 16))
subMatriz(m25, 0, 0, 1) // Vector(Vector(2))

//sumMatriz

val m26 = Vector(Vector(1, 2), Vector(3, 4))
val m27 = Vector(Vector(5, 6), Vector(7, 8))
sumMatriz(m26, m27) // Vector(Vector(6, 8), Vector(10, 12))

val m28 = Vector(Vector(-1, 2), Vector(3, -4))
val m29 = Vector(Vector(1, -2), Vector(-3, 4))
sumMatriz(m28, m29) // Vector(Vector(0, 0), Vector(0, 0))

val m30 = Vector(Vector(0, 0), Vector(0, 0))
val m31 = Vector(Vector(1, 2), Vector(3, 4))
sumMatriz(m30, m31) // Vector(Vector(1, 2), Vector(3, 4))

val m32 = Vector(Vector(2, -1), Vector(0, 3))
val m33 = Vector(Vector(-3, 2), Vector(1, -4))
sumMatriz(m32, m33) // Vector(Vector(-1, 1), Vector(1, -1))

val m34 = Vector(Vector(10, -5, 2, -8), Vector(-3, 7, -1, 4), Vector(6, -2, 9, -4), Vector(-9, 4, -6, 1))
val m35 = Vector(Vector(-10, 5, -2, 8), Vector(3, -7, 1, -4), Vector(-6, 2, -9, 4), Vector(9, -4, 6, -1))
sumMatriz(m34, m35) // Vector(Vector(0, 0, 0, 0), Vector(0, 0, 0, 0), Vector(0, 0, 0, 0), Vector(0, 0, 0, 0))

//multMatrizRec

val m36 = Vector(Vector(3, 1), Vector(2, 4))
val m37 = Vector(Vector(5, 2), Vector(1, 3))
multMatrizRec(m36, m37) // Vector(Vector(16, 9), Vector(14, 16))

val m38 = Vector(Vector(-1, 2), Vector(3, -2))
val m39 = Vector(Vector(4, -1), Vector(-2, 5))
multMatrizRec(m38, m39) // Vector(Vector(-8, 11), Vector(16, -13))

val m40 = Vector(Vector(2, -2), Vector(-2, 2))
val m41 = Vector(Vector(3, 3), Vector(3, 3))
multMatrizRec(m40, m41) // Vector(Vector(0, 0), Vector(0, 0))

val m42 = Vector(Vector(0, 5), Vector(-3, 0))
val m43 = Vector(Vector(2, -1), Vector(4, 0))
multMatrizRec(m42, m43) // Vector(Vector(20, 0), Vector(-6, 3))

val m44 = Vector(Vector(1, 0, 1, 0), Vector(0, 1, 0, 1), Vector(1, 1, 0, 0), Vector(0, 0, 1, 1))
val m45 = Vector(Vector(2, 1, 2, 1), Vector(1, 2, 1, 2), Vector(3, 0, 3, 0), Vector(0, 3, 0, 3))
multMatrizRec(m44, m45) //Vector(Vector(5, 1, 5, 1),Vector(1, 5, 1, 5),Vector(3, 3, 3, 3),Vector(3, 3, 3, 3))

val m46 = Vector(Vector(1, -2, 3, -4), Vector(-5, 6, -7, 8), Vector(9, -10, 11, -12), Vector(-13, 14, -15, 16))
val m47 = Vector(Vector(-1, 2, -3, 4), Vector(5, -6, 7, -8), Vector(-9, 10, -11, 12), Vector(13, -14, 15, -16))
multMatrizRec(m46, m47) //Vector(Vector(-90, 100, -110, 120),Vector(202, -228, 254, -280),Vector(-314, 356, -398, 440),Vector(426, -484, 542, -600))

//restaMatriz

val m48 = Vector(Vector(4, 6), Vector(8, 10))
val m49 = Vector(Vector(1, 2), Vector(3, 4))
restaMatriz(m48, m49) // Vector(Vector(3, 4), Vector(5, 6))

val m50 = Vector(Vector(-1, 3), Vector(5, -7))
val m51 = Vector(Vector(2, -4), Vector(-6, 8))
restaMatriz(m50, m51) // Vector(Vector(-3, 7), Vector(11, -15))

val m52 = Vector(Vector(0, 0), Vector(0, 0))
val m53 = Vector(Vector(2, 4), Vector(6, 8))
restaMatriz(m52, m53) // Vector(Vector(-2, -4), Vector(-6, -8))

val m54 = Vector(Vector(10, 20, 30, 40), Vector(50, 60, 70, 80), Vector(90, 100, 110, 120), Vector(130, 140, 150, 160))
val m55 = Vector(Vector(5, 5, 5, 5), Vector(10, 10, 10, 10), Vector(15, 15, 15, 15), Vector(20, 20, 20, 20))
restaMatriz(m54, m55) // Vector(Vector(5, 15, 25, 35), Vector(40, 50, 60, 70), Vector(75, 85, 95, 105), Vector(110, 120, 130, 140))

val m56 = Vector(Vector(10, 20, 30, 40), Vector(50, 60, 70, 80), Vector(90, 100, 110, 120), Vector(130, 140, 150, 160))
val m57 = Vector(Vector(1, 2, 3, 4), Vector(5, 6, 7, 8), Vector(9, 10, 11, 12), Vector(13, 14, 15, 16))
restaMatriz(m56, m57) // Vector(Vector(9, 18, 27, 36), Vector(45, 54, 63, 72), Vector(81, 90, 99, 108), Vector(117, 126, 135, 144))

// multStrassen

val m58 = Vector(Vector(2, 0), Vector(0, 3))
val m59 = Vector(Vector(4, 0), Vector(0, 5))
multStrassen(m58, m59) // Vector(Vector(8, 0), Vector(0, 15))

val m60 = Vector(Vector(0, 1), Vector(1, 0))
val m61 = Vector(Vector(5, 6), Vector(7, 8))
multStrassen(m60, m61) // Vector(Vector(7, 8), Vector(5, 6))

val m62 = Vector(Vector(1, 1), Vector(1, 1))
val m63 = Vector(Vector(2, 2), Vector(2, 2))
multStrassen(m62, m63) // Vector(Vector(4, 4), Vector(4, 4))

val m64 = Vector(Vector(-2, 1), Vector(4, -3))
val m65 = Vector(Vector(1, -2), Vector(-3, 4))
multStrassen(m64, m65) // Vector(Vector(-5, 8), Vector(13, -20))

val m66 = Vector(Vector(1, -1, 1, -1), Vector(-1, 1, -1, 1), Vector(1, -1, 1, -1), Vector(-1, 1, -1, 1))
val m67 = Vector(Vector(2, 2, 2, 2), Vector(2, 2, 2, 2), Vector(2, 2, 2, 2), Vector(2, 2, 2, 2))
multStrassen(m66, m67) // Vector(Vector(0, 0, 0, 0), Vector(0, 0, 0, 0), Vector(0, 0, 0, 0), Vector(0, 0, 0, 0))

val m68 = Vector(Vector(1, 2, 0, 0), Vector(3, 4, 0, 0), Vector(0, 0, 5, 6), Vector(0, 0, 7, 8))
val m69 = Vector(Vector(9, 10, 0, 0), Vector(11, 12, 0, 0), Vector(0, 0, 13, 14), Vector(0, 0, 15, 16))
multStrassen(m68, m69) // Vector(Vector(31, 34, 0, 0), Vector(71, 78, 0, 0), Vector(0, 0, 155, 166), Vector(0, 0, 211, 226))