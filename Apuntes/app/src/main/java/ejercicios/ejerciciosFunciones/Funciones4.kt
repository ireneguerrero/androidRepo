package ejerciciosFunciones

fun retornarSuperficie(lado: Int): Int {
    val sup = lado * lado
    return sup
}

fun main(parametro: Array<String>) {
    print("Ingrese el valor del lado del cuadrado: ")
    val la = readln().toInt()
    val superficie = retornarSuperficie(la)
    println("La superficie del cuadrado es $superficie")
}