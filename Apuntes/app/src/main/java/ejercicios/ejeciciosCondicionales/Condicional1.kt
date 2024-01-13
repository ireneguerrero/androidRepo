package ejeciciosCondicionales

fun main() {
    print("Escribe el sueldo de la persona: ")
    val sueldo = readLine()?.toDoubleOrNull() ?: 0.0

    if (sueldo > 3000) {
        println("Debe abonar impuestos.")
    } else {
        println("No debe abonar impuestos.")
    }
}
