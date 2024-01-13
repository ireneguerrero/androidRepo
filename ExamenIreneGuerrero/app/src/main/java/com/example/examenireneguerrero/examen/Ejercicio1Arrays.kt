package com.example.examenireneguerrero.examen

fun main() {
    //creación de un array bidimensional
    val array = arrayOf(
        arrayOf("Juan", "Eva", "Ana", "Pedro"),
        floatArrayOf(1700.0f, 2800.0f, 1900.0f, 2300.0f)
    )

    val nombres = array[0] as Array<String>

    println("Nombres: ${nombres.joinToString(", ")}")

    //cálculos con los importes
    val importes = array[1] as FloatArray
    val sumaImportes = importes.sum()
    val mediaImportes = importes.average()
    println("SalarioBase: ${importes.joinToString(", ")}")
    println("Salario Medio: $mediaImportes €")
    println("Salario Suma: $sumaImportes €")
}