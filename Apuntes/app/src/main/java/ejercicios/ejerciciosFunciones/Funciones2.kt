package ejerciciosFunciones

fun mostrarMensaje(mensaje: String) {
    println("*************************************************")
    println(mensaje)
    println("*************************************************")
}

fun cargarSumar2() {
    print("Ingrese el primer valor: ")
    val valor1 = readln().toInt()
    print("Ingrese el segundo valor: ")
    val valor2 = readln().toInt()
    val suma = valor1 + valor2
    println("La suma de los dos valores es: $suma")
}

fun main(parametro: Array<String>) {
    mostrarMensaje("El programa calcula la suma de dos valores ingresados por teclado")
    cargarSumar2()
    mostrarMensaje("Gracias por utilizar este programa")
}