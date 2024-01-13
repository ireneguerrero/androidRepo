package simulacroExamen

fun mostrar(lista: List<Any>) {
    for (elemento in lista) {
        println(elemento)
    }
}

fun main() {
    var centro: MutableList<Any>
    centro = mutableListOf("MATEMÁTICAS", "Alberto", 5, "LENGUA", "Laura", 6)
    println("Lista original***************************")
    mostrar(centro)

    //modificando
    centro[0] = "HISTORIA"
    centro[3] = "FÍSICA"
    println("Modificando asignaturas******************")
    mostrar(centro)
    centro.add("QUÍMICA")
    centro.add("Cristina")
    centro.add(7)
    println("Añadiendo********************************")
    mostrar(centro)
}