package ejeciciosCondicionales

fun main() {
    print("Escribe un valor entero: ")
    val numero = readLine()?.toIntOrNull() ?: 0

    val resultado = if (numero % 2 == 0) {
        val cuadrado = numero * numero
        println("Se calculó el cuadrado.")
        cuadrado
    } else {
        val cubo = numero * numero * numero
        println("Se calculó el cubo.")
        cubo
    }

    println("El resultado es: $resultado")
}
