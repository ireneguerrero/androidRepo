package com.example.examenireneguerrero.examen

//creación de la función mostrar para que se muestren los elementos de la lista
fun mostrar(lista: List<Any>) {
    for (elemento in lista) {
        println(elemento)
    }
}

fun main() {
    //definición de la variable centro que contiene la lista mutable
    //impresión de la lista original
    var centro: MutableList<Any>
    centro = mutableListOf("MATEMÁTICAS", "Alberto", 5, "LENGUA", "Laura", 6)
    println("Lista original***************************")
    mostrar(centro)

    //modificando
    centro[0] = "HISTORIA"
    centro[3] = "FÍSICA"
    println("Modificando asignaturas******************")
    mostrar(centro)
    //añadiendo
    centro.add("QUÍMICA")
    centro.add("Cristina")
    centro.add(7)
    println("Añadiendo********************************")
    mostrar(centro)
}