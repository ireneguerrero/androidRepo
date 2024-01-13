package com.example.apuntes.apuntes.apuntes1

fun main() {
    //var te permite modificar la variable y val es una constante

    //println("Hola Mundo")

    //definición de variables
    var valor1: Int
    var valor2: Int
    var resultado: Int
    //asignación de valores
    valor1 = 100
    valor2 = 400
    //imprimir por pantalla
    resultado = valor1 + valor2
    println("La suma de $valor1 + $valor2 es $resultado")
    resultado = valor1 * valor2
    println("El producto de $valor1 * $valor2 es $resultado")
    resultado = valor1 - valor2
    println("La resta de $valor1 - $valor2 es $resultado")
    resultado = valor1 / valor2
    println("La división de $valor1 / $valor2 es $resultado")

    //concatenación de cosas que no son String
    var st: String = "" + 3

    print("Ingrese el sueldo del empleado: ")
    val sueldo = readln().toDouble()
    if (sueldo > 3000) {
        println("Debe pagar impuestos")
    } else {
        println("No debe pagar impuestos")
    }

    print("Ingresa un número entero: ")
    val numero1 = readln().toInt()
    print("Ingresa otro número entero: ")
    val numero2 = readln().toInt()
    if (numero1 > numero2) {
        println("El número $numero1 es mayor que el número $numero2")
    } else {
        println("El número $numero2 es mayor que el número $numero1")
    }
}