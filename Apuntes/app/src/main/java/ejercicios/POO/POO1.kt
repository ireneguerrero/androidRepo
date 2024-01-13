package POO

class Persona {
    var nombre: String = ""
    var edad: Int = 0
    var apellido: String = ""
    var direccion: String = ""
    var ciudad: String = ""
    var provincia: String = ""
    var cp: Int = 0

    fun inicializar(nombre: String, edad: Int, apellido: String, direccion: String, ciudad: String, provincia: String, cp: Int) {
        this.nombre = nombre
        this.edad = edad
        this.apellido = apellido
        this.direccion = direccion
        this.ciudad = ciudad
        this.provincia = provincia
        this.cp = cp
    }

    fun imprimir() {
        println("Nombre completo: $nombre $apellido, tiene una edad de: $edad y vive en: $direccion, ciudad: $ciudad, provincia: $provincia, código postal: $cp")
    }

    fun esMayorEdad() {
        if (edad >= 18) {
            println("$nombre $apellido es mayor de edad")
        } else {
            println("$nombre $apellido es menor de edad")
        }
    }
}

fun main() {
    val persona1: Persona
    persona1 = Persona()
    persona1.inicializar("Juan", 12, "García", "Calle García", "Málaga", "Málaga", 29009)
    persona1.imprimir()
    persona1.esMayorEdad()

    val persona2: Persona
    persona2 = Persona()
    persona2.inicializar("Ana", 50, "Pérez", "Calle Pérez", "Pamplona", "Navarra", 31180)
    persona2.imprimir()
    persona2.esMayorEdad()
}