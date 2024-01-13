package ejerciciosBucles

fun main() {
    var suma = 0.0

    for (i in 1..10) {
        print("Escribe el valor $i: ")
        val valor = readLine()?.toDoubleOrNull() ?: 0.0

        suma += valor
    }

    val promedio = suma / 10

    println("La suma de los valores es: $suma")
    println("El promedio de los valores es: $promedio")
}
