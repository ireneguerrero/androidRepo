package com.example.apuntes.apuntes.apuntes7

// interfaces

interface ProductoElectronico {
    //Propiedades de la interfaz
    val nombre: String
    val fabricante: String
    val precio: Double

    //Métodos de la interfaz
    fun encender()
    fun apagar()
}

//Clase que implementa la interfaz PoductoElectronico
//tenemos que usar override para pisar tanto las funciones abstractas
//como los argumentos que estamos usando

class Televisor(
    override val nombre: String,
    override val fabricante: String,
    override val precio: Double
) : ProductoElectronico {
    //Implementación de los métodos encender() y apagar()
    override fun encender() {
        println("Encendiendo el televisor $nombre fabricado por $fabricante")
    }

    override fun apagar() {
        println("Apagando el televisor $nombre")
    }
}

fun main() {
    //Crear una instancia de la clase Televisor
    val miTelevisor = Televisor("Smart TV", "Samsung", 799.99)

    //Acceder a las propiedades y métodos de la interfaz
    println("Nombre: ${miTelevisor.nombre}")
    println("Fabricante: ${miTelevisor.fabricante}")
    println("Precio: ${miTelevisor.precio}")

    //Llamar a los métodos encender() y apagar()
    miTelevisor.encender()
    miTelevisor.apagar()
}