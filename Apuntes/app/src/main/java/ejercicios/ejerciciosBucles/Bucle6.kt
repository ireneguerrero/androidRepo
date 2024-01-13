package ejerciciosBucles

fun main() {
    print("Escribe la coordenada x: ")
    val x = readLine()?.toIntOrNull() ?: 0

    print("Escribe la coordenada y: ")
    val y = readLine()?.toIntOrNull() ?: 0

    val cuadrante = when {
        x > 0 && y > 0 -> "1º Cuadrante"
        x < 0 && y > 0 -> "2º Cuadrante"
        x < 0 && y < 0 -> "3º Cuadrante"
        x > 0 && y < 0 -> "4º Cuadrante"
        x == 0 && y != 0 -> "Se encuentra en el eje Y"
        x != 0 && y == 0 -> "Se encuentra en el eje X"
        x == 0 && y == 0 -> "El punto se encuentra en el origen"
        else -> "Punto no clasificado"
    }

    println("El punto ($x, $y) se encuentra en: $cuadrante")
}
