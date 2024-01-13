package ejerciciosFunciones

fun tituloSubrayado(titulo: String, caracter: String = "*") {
    println(titulo)
    for (i in 1..titulo.length) {
        print(caracter)
    }
    println()
}

fun main(parametro: Array<String>) {
    tituloSubrayado("Sistema de Administración")
    tituloSubrayado("Ventas", "-")
}