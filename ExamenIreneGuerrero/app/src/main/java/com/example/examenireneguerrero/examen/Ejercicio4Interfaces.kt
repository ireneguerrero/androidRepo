package com.example.examenireneguerrero.examen

//creación de interfaces
interface Jugable {
    fun jugar() {
        println("Iniciando juego")
    }
}

interface SonidoAmbiente {
    fun reproducirSonido() {
        println("Sonido")
    }
}

interface GuardadoPartida {
    fun guardarPartida() {
        println("Guardando")
    }
}

//creación de clases heredando de las interfaces
class Aventura(val nombre: String, val jugador: String) : Jugable, SonidoAmbiente, GuardadoPartida {
    override fun jugar() {
        println("Iniciando juego de aventura: $nombre para el jugador $jugador")
    }

    override fun reproducirSonido() {
        println("Sonido misterioso de la aventura")
    }

    override fun guardarPartida() {
        println("Guardando el proceso del juego de aventura")
    }
}

class Deportes(val nombre: String, val jugador: String) : Jugable, SonidoAmbiente, GuardadoPartida {
    override fun jugar() {
        println("Iniciando juego de deportes: $nombre para el jugador $jugador")
    }

    override fun reproducirSonido() {
        println("Sonidos emocionantes de la competición deportiva")
    }

    override fun guardarPartida() {
        println("Guardando progreso del juego de deportes")
    }
}

class Estrategia(val nombre: String, val jugador: String) : Jugable, SonidoAmbiente,
    GuardadoPartida {
    override fun jugar() {
        println("Iniciando juego de estrategia: $nombre para el jugador $jugador")
    }

    override fun reproducirSonido() {
        println("Sonidos tácticos del juego de estrategia")
    }

    override fun guardarPartida() {
        println("Guardando progreso del juego de estrategia")
    }
}

fun main() {
    //definir variables con sus clases
    val aventura = Aventura("El Viaje Épico", "Cristina")
    val deportes = Deportes("Campeonato Virtual", "Alberto")
    val estrategia = Estrategia("Reinos en Guerra", "Laura")

    //imprimir
    aventura.jugar()
    aventura.reproducirSonido()
    aventura.guardarPartida()
    println("-----------------------------")
    deportes.jugar()
    deportes.reproducirSonido()
    deportes.guardarPartida()
    println("-----------------------------")
    estrategia.jugar()
    estrategia.reproducirSonido()
    estrategia.guardarPartida()
}