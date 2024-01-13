package ejeciciosCondicionales

fun main() {
    print("Escribe la primera nota: ")
    val nota1 = readLine()?.toDoubleOrNull() ?: 0.0

    print("Escribe la segunda nota: ")
    val nota2 = readLine()?.toDoubleOrNull() ?: 0.0

    print("Escribe la tercera nota: ")
    val nota3 = readLine()?.toDoubleOrNull() ?: 0.0

    val promedio = (nota1 + nota2 + nota3) / 3

    val calificacion = when {
        promedio >= 9 -> "SOBRESALIENTE"
        promedio >= 7 && promedio < 9 -> "NOTABLE"
        promedio >= 6 && promedio < 7 -> "BIEN"
        promedio >= 5 && promedio < 6 -> "SUFICIENTE"
        promedio >= 0 && promedio < 5 -> "SUSPENSO"
        else -> "Calificación no definida"
    }

    println("El promedio es: $promedio")
    println("Calificación: $calificacion")
}
