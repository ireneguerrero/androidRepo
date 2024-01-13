package ejerciciosBucles

fun main() {
    print("Escribe la cantidad de piezas a procesar: ")
    val cantidadPiezas = readLine()?.toIntOrNull()

    if (cantidadPiezas == null || cantidadPiezas <= 0) {
        println("Escribe una cantidad válida de piezas.")
        return
    }

    var piezasAptas = 0

    for (i in 1..cantidadPiezas) {
        print("Escribe la longitud de la pieza $i: ")
        val longitud = readLine()?.toDoubleOrNull()

        if (longitud == null) {
            println("Escribe una longitud válida.")
            return
        }

        if (longitud in 1.20..1.30) {
            piezasAptas++
        }
    }

    println("Cantidad de piezas aptas: $piezasAptas")

}