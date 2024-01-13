package com.example.apuntes.apuntes.apuntes4

import java.util.Scanner
import kotlin.random.Random

fun sumar(a: Int, b: Int): Int {
    var resultado = a + b
    return resultado

}

// Resta

fun resta(c: Int, d: Int): Int {
    var resultadoResta = c - d
    return resultadoResta
}

// Multiplicacion

fun multiplicacion(e: Int, f: Int): Int {
    var resultadoMultipliacion = e * f
    return resultadoMultipliacion
}

//Division

fun division(g: Int, h: Int): Int {
    var resultadoDivision = g / h
    return resultadoDivision
}

//Piedra, Papel y Tijera

fun juegoPiedraPapelTijera() {
    val eleccionUsuario = obtenerEleccionUsuario()
    val eleccionComputadora = obtenerEleccionComputadora()

    println("Tu elección: $eleccionUsuario")
    println("Elección de la computadora: $eleccionComputadora")

    val resultado = determinarGanador(eleccionUsuario, eleccionComputadora)
    println("Resultado: $resultado")
}

fun obtenerEleccionUsuario(): String {
    println("Elige piedra, papel o tijera:")
    return readLine().orEmpty().toLowerCase()
}

fun obtenerEleccionComputadora(): String {
    val opciones = listOf("piedra", "papel", "tijera")
    return opciones[Random.nextInt(opciones.size)]
}

fun determinarGanador(eleccionUsuario: String, eleccionComputadora: String): String {
    if (eleccionUsuario == eleccionComputadora) {
        return "Empate"
    } else if (
        (eleccionUsuario == "piedra" && eleccionComputadora == "tijera") ||
        (eleccionUsuario == "papel" && eleccionComputadora == "piedra") ||
        (eleccionUsuario == "tijera" && eleccionComputadora == "papel")
    ) {
        return "¡Ganaste!"
    } else {
        return "¡La computadora ganó!"
    }
}

// Fibonacci

fun fibonacci() {
    // Solicitar al usuario que ingrese el número de elementos de la secuencia de Fibonacci
    println("Ingrese el número de elementos de la secuencia de Fibonacci que desea calcular:")
    val n = readLine()?.toIntOrNull() ?: 0

    if (n > 0) {
        println("Los primeros $n números de la secuencia de Fibonacci son: ${calcularFibonacci(n)}")
    } else {
        println("Por favor, ingrese un número válido mayor que 0.")
    }
}

fun calcularFibonacci(n: Int): List<Int> {
    val fibonacciList = mutableListOf<Int>()

    var a = 0
    var b = 1

    for (i in 1..n) {
        fibonacciList.add(a)
        val sum = a + b
        a = b
        b = sum
    }

    return fibonacciList
}

//numero naturales

fun sumaNaturales(n: Int): Int {
    return if (n <= 0) {
        0
    } else {
        n + sumaNaturales(n - 1)
    }
}

fun main() {

    println("Indicame un numero: ")
    var numero1 = readLine()?.toIntOrNull() ?: 0
    print("Indicame otro numero: ")
    var numero2 = readLine()?.toIntOrNull() ?: 0
    var resultado = sumar(numero1, numero2)
    var resultadoResta = resta(numero1, numero2)
    var resultadoMultipliacion = multiplicacion(numero1, numero2)
    var resultadoDivision = division(numero1, numero2)

    println("La suma es $resultado")
    println("La resta es $resultadoResta")
    println("La multiplicacion es $resultadoMultipliacion")
    println("La division es $resultadoDivision")

    // -----------------------------------------------------------

    val scanner = Scanner(System.`in`)

    // Solicitar al usuario que ingrese dos números
    println("Ingrese el primer número:")
    val num1 = scanner.nextInt()

    println("Ingrese el segundo número:")
    val num2 = scanner.nextInt()

    var continuar = true;

    while (continuar) {

        // Mostrar el menú de operaciones
        println("Seleccione la operación:")
        println("1. Sumar")
        println("2. Restar")
        println("3. Dividir")
        println("4. multiplicacion")
        println("5. juego piedra, papel y tijera")
        println("6. Fibonacci")
        println("7. Salir")

        // Leer la opción del usuario
        val opcion = scanner.nextInt()

        // Realizar la operación seleccionada
        when (opcion) {
            1 -> sumar(num1, num2)
            2 -> resta(num1, num2)
            3 -> division(num1, num2)
            4 -> multiplicacion(num1, num2)
            5 -> juegoPiedraPapelTijera()
            6 -> fibonacci()
            7 -> {
                println("Saliendo del programa.")
                continuar = false
            }
        }

        if (opcion == 1) {
            println("La suma es ${sumar(num1, num2)}")
        } else if (opcion == 2) {
            println("La resta es ${resta(num1, num2)}")
        } else if (opcion == 3) {
            println("La division es ${division(num1, num2)}")
        } else if (opcion == 4) {
            println("La multiplicacion es ${multiplicacion(num1, num2)}")
        }
    }

    // --------------------------------------------------------------------------------

    val ale1 = Random.nextInt(1, 4)
    println("El número elegido es $ale1")

    val resultado1 = sumaNaturales(num1)
    println("La suma de los primeros $num1 números naturales es: $resultado1")


}