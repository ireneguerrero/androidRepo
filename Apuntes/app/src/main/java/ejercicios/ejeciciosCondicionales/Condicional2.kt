package ejeciciosCondicionales

fun main() {
    print("Escribe el primer número entero: ")
    val numero1 = readLine()?.toIntOrNull() ?: 0

    print("Escribe el segundo número entero: ")
    val numero2 = readLine()?.toIntOrNull() ?: 0

    val mayor = if (numero1 > numero2) numero1 else numero2

    println("El número mayor es: $mayor")
}
