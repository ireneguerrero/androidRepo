package ejerciciosBucles

fun main() {
    print("Escribe la primera nota: ")
    val nota1 = readLine()?.toDoubleOrNull() ?: 0.0

    print("Escribe la segunda nota: ")
    val nota2 = readLine()?.toDoubleOrNull() ?: 0.0

    print("Escribe la tercera nota: ")
    val nota3 = readLine()?.toDoubleOrNull() ?: 0.0

    val promedio = (nota1 + nota2 + nota3) / 3

    val clasificacion = when {
        promedio >= 7 -> "Promocionado"
        promedio >= 4 && promedio < 7 -> "Regular"
        promedio < 4 -> "Reprobado"
        else -> "Clasificación no definida"
    }

    println("El promedio es: $promedio")
    println("Clasificación: $clasificacion")
}
