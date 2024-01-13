package ejerciciosBucles

fun main() {
    var suma = 0.0
    var cantidad = 0
    var numero: Double

    do {
        print("Escribe un número (o 0 para finalizar): ")
        numero = readLine()?.toDoubleOrNull() ?: 0.0

        if (numero != 0.0) {
            suma += numero
            cantidad++
        }

    } while (numero != 0.0)

    val promedio = if (cantidad > 0) suma / cantidad else 0.0

    println("El promedio de los números es: $promedio")
}
