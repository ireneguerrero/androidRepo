package ejerciciosArrays

fun main() {
    // Devolver elemento
    val numeros = arrayOf(1, 2, 3, 4, 5)

    val elementoABuscar = 3

    val posicion = buscarPosicion(numeros, elementoABuscar)

    if (posicion != -1) {
        println("El elemento $elementoABuscar se encuentra en la posición $posicion.")
    } else {
        println("El elemento $elementoABuscar no se encuentra en el array.")
    }
}

fun buscarPosicion(array: Array<Int>, elemento: Int): Int {
    for (i in array.indices) {
        if (array[i] == elemento) {
            return i
        }
    }

    return -1 // Devolver -1 si el elemento no se encuentra en el array
}
