package com.example.examenireneguerrero.examen

//creación de la clase padre
open class Deporte(val nombre: String, val tipoTerreno: String) {
    open fun mostrarDetalles() {
        println("Deporte: $nombre, Tipo de terreno: $tipoTerreno")
    }
}

//clases hijas que heredan de la clase padre
class Futbol(
    nombre: String,
    tipoTerreno: String,
    val numeroJugadores: Int,
    val esCampoGrande: Boolean
) : Deporte(nombre, tipoTerreno) {
    //sobreescribir función mostrarDetalles para personalizarla
    override fun mostrarDetalles() {
        super.mostrarDetalles()
        println("Número de jugadores: $numeroJugadores, Campo grande: $esCampoGrande")
    }
}

class Baloncesto(
    nombre: String,
    tipoTerreno: String,
    val alturaCanasta: Double,
    val esDeporteEquipo: Boolean
) : Deporte(nombre, tipoTerreno) {
    override fun mostrarDetalles() {
        super.mostrarDetalles()
        println("Altura de la canasta: $alturaCanasta metros, Deporte de equipo: $esDeporteEquipo")
    }
}

class Balonmano(
    nombre: String,
    tipoTerreno: String,
    val esDeporteOlimpico: Boolean,
    val esContacto: Boolean
) : Deporte(nombre, tipoTerreno) {
    override fun mostrarDetalles() {
        super.mostrarDetalles()
        println("Deporte Olímpico: $esDeporteOlimpico, Contacto: $esContacto")
    }
}

class Voleibol(
    nombre: String,
    tipoTerreno: String,
    val esDeportePlaya: Boolean,
    val numeroJugadoresEquipo: Int
) : Deporte(nombre, tipoTerreno) {
    override fun mostrarDetalles() {
        super.mostrarDetalles()
        println("Deporte de playa: $esDeportePlaya, Número de jugadores por equipo: $numeroJugadoresEquipo")
    }
}


fun main() {
    //definir las variables con sus respectivas clases
    val futbol = Futbol("Fútbol", "Campo", 11, true)
    val baloncesto = Baloncesto("Baloncesto", "Pista", 3.05, true)
    val balonmano = Balonmano("Balonmano", "Pista", true, true)
    val voleibol = Voleibol("Voleibol", "Arena", true, 6)

    //imprimir
    futbol.mostrarDetalles()
    println("--------------------------------")
    baloncesto.mostrarDetalles()
    println("--------------------------------")
    balonmano.mostrarDetalles()
    println("--------------------------------")
    voleibol.mostrarDetalles()
}