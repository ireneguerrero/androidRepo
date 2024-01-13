package com.example.examenireneguerrero.examen

import kotlin.random.Random

//función que determina el ganador según la elección que haga
fun determinarGanador(eleccionUsuario: String?, eleccionMaquina: String): String {
    if (eleccionUsuario == eleccionMaquina) {
        return "Empate"
    }

    return when (eleccionUsuario) {
        "piedra" -> if (eleccionMaquina == "tijeras") "Usuario" else "Máquina"
        "papel" -> if (eleccionMaquina == "piedra") "Usuario" else "Máquina"
        "tijeras" -> if (eleccionMaquina == "papel") "Usuario" else "Máquina"
        else -> "Desconocido"
    }
}

fun main() {
    println("Bienvenido al juego Piedra, Papel o Tijeras")

    print("Ingrese el número de partidas a jugar: ")
    val numPartidas = readLine()?.toIntOrNull() ?: 0
    println()

    var partidasGanadasUsuario = 0
    var partidasGanadasMaquina = 0

    // jugar las partidas
    for (i in 1..numPartidas) {
        println("--- Partida $i ---")

        // hacer al usuario elegir entre piedra, papel o tijeras
        print("Elija piedra, papel o tijeras: ")
        val eleccionUsuario = readLine()?.toLowerCase()

        // generar aleatoriamente la elección de la máquina
        val eleccionesPosibles = listOf("piedra", "papel", "tijeras")
        val eleccionMaquina = eleccionesPosibles[Random.nextInt(eleccionesPosibles.size)]

        // determinar el ganador
        val resultadoPartida = determinarGanador(eleccionUsuario, eleccionMaquina)

        // imprimir el resultado de la partida
        println("Usuario elige: $eleccionUsuario")
        println("Máquina elige: $eleccionMaquina")
        println("Resultado: $resultadoPartida")
        println()

        // actualizar las partidas ganadas
        when (resultadoPartida) {
            "Usuario" -> partidasGanadasUsuario++
            "Máquina" -> partidasGanadasMaquina++
        }
    }

    // imprimir el resultado final
    println("--- Resultado Final ---")
    println("Partidas ganadas por el usuario: $partidasGanadasUsuario")
    println("Partidas ganadas por la máquina: $partidasGanadasMaquina")
    if (partidasGanadasUsuario > partidasGanadasMaquina) {
        println("Ha ganado el usuario")
    } else if (partidasGanadasMaquina > partidasGanadasUsuario) {
        println("Ha ganado la máquina")
    } else {
        println("Empate")
    }

}


