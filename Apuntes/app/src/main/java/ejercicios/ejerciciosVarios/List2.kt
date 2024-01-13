package ejerciciosVarios

//lista mutable(modificable)
fun main() {
    var col4: List<Any>
    col4 = mutableListOf(1, "Alberto", 2, "Laura", 3, "Cristina")

    println(col4)
    println()
    //añadimos un último elemento
    col4.add(4)
    col4.add("Pablo") //el índice es para indicar la posición y si no pones nada te lo pone al final
    for (element in col4) {
        println(element)
    }
    println()
    //reemplazamos el primer elemento
    col4[1] = "Pedro"
    for (element in col4) {
        println(element)
    }
}