package ejerciciosHerencias

// Clase base (superclase)
open class Vehiculo(val modelo: String, val anio: Int) {
    fun conducir() {
        println("El vehículo está en movimiento")
    }
}

// Clase derivada (subclase) que hereda de Vehiculo
class Coche(modelo: String, anio: Int, val fabricante: String) : Vehiculo(modelo, anio) {
    fun hacerSonido() {
        println("El coche hace un sonido de motor")
    }
}

class Motocicleta(modelo: String, anio: Int, val tipo: String) : Vehiculo(modelo, anio) {
    fun hacerCaballito() {
        println("La moto hace un caballito")
    }
}

fun main() {
    // Crear una instancia de la clase derivada (Coche)
    val miCoche = Coche("Civic", 2022, "Honda")

    // Acceder a las propiedades y métodos de la clase base (Vehiculo)
    println("Modelo: ${miCoche.modelo}")
    println("Año: ${miCoche.anio}")

    // Acceder a las propiedades y métodos de la clase derivada (Coche)
    println("Fabricante: ${miCoche.fabricante}")

    // Llamar a un método de la clase base
    miCoche.conducir()

    // Llamar a un método de la clase derivada
    miCoche.hacerSonido()
    println()

    val miMoto = Motocicleta("Urbana", 2010, "Yamaha")
    println("Modelo: ${miMoto.modelo}")
    println("Año: ${miMoto.anio}")
    println("Fabricante: ${miMoto.tipo}")
    miMoto.conducir()
    miMoto.hacerCaballito()
}