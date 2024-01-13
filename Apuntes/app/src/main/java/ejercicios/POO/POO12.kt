package POO

class PersonaDelMundo(val nombre: String, val apellidos: String, val edad: Int) {
    constructor(nombre: String, apellidos: String) : this(nombre, apellidos, 0)

    fun mostrarInformacion() {
        println("Nombre: $nombre")
        println("Apellidos: $apellidos")
        println("Edad: $edad")
        println()
    }
}

fun main() {
    val persona1 = PersonaDelMundo("Juan", "Pérez", 30)
    persona1.mostrarInformacion()

    val persona2 = PersonaDelMundo("Rebeca", "Martínez")
    persona2.mostrarInformacion()
}