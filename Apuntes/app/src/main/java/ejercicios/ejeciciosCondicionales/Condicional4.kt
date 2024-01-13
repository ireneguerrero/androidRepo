package ejeciciosCondicionales

fun main() {
    print("Escribe el primer valor entero: ")
    val valor1 = readLine()?.toIntOrNull() ?: 0

    print("Escribe el segundo valor entero: ")
    val valor2 = readLine()?.toIntOrNull() ?: 0

    val mayor = if (valor1 > valor2) valor1 else valor2

    println("El valor mayor es: $mayor")
}
