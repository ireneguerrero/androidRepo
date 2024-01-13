package ejerciciosArrays

fun main() {
    // Promedio de elementos
    val numeros = arrayOf(1, 2, 3, 4, 5)

    val promedio = calcularPromedio(numeros)

    println("El promedio de los elementos es: $promedio")
}

fun calcularPromedio(array: Array<Int>): Double {
    if (array.isEmpty()) {
        return 0.0
    }

    var suma = 0

    for (elemento in array) {
        suma += elemento
    }

    return suma.toDouble() / array.size
}
