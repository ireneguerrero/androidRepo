package ejerciciosBucles

fun main() {
    var numero: Int

    do {
        print("Escribe un número entre 0 y 999 (o 0 para salir): ")
        numero = readLine()?.toIntOrNull() ?: 0

        if (numero < 0 || numero > 999) {
            println("Escribe un número válido entre 0 y 999.")
            continue
        }

        val cantidadDigitos = contarDigitos(numero)

        println("El número $numero tiene $cantidadDigitos ${if (cantidadDigitos == 1) "dígito" else "dígitos"}.")

    } while (numero != 0)
}

fun contarDigitos(numero: Int): Int {
    return when {
        numero == 0 -> 1
        numero in 1..9 -> 1
        numero in 10..99 -> 2
        numero in 100..999 -> 3
        else -> 0
    }
}
