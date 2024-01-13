package ejerciciosArrays

fun main() {
    // Suma de elementos
    val numeros = arrayOf(1, 2, 3, 4, 5)

    val suma = calcularSuma(numeros)

    println("La suma de los elementos es: $suma")
}

fun calcularSuma(array: Array<Int>): Int {
    var suma = 0

    for (elemento in array) {
        suma += elemento
    }

    return suma
}
