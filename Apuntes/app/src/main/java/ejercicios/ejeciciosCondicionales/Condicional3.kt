package ejeciciosCondicionales

fun main() {
    print("Escribe el primer valor entero: ")
    val valor1 = readLine()?.toIntOrNull() ?: 0

    print("Escribe el segundo valor entero: ")
    val valor2 = readLine()?.toIntOrNull() ?: 0

    if (valor1 < valor2) {
        val suma = valor1 + valor2
        val resta = valor2 - valor1

        println("La suma es: $suma")
        println("La resta es: $resta")
    } else {
        val producto = valor1 * valor2

        val division = if (valor2 != 0) valor1.toDouble() / valor2.toDouble() else null

        println("El producto es: $producto")

        if (division != null) {
            println("La división es: $division")
        } else {
            println("No se puede calcular la división ya que el segundo valor es cero.")
        }
    }
}
