package ejerciciosHerencias

open class Personaje(val nombre: String, var nivel: Int) {
    open fun atacar() {
        println("$nombre realiza un ataque básico")
    }

    fun subirNivel() {
        nivel++
        println("$nombre ha subido al nivel $nivel")
    }
}

class Guerrero(nombre: String, nivel: Int, val arma: String) : Personaje(nombre, nivel) {
    override fun atacar() {
        println("$nombre ataca con $arma")
    }

    fun usarHabilidadEspecial() {
        println("$nombre utiliza una habilidad especial de guerrero")
    }
}

class Mago(nombre: String, nivel: Int, val hechizo: String) : Personaje(nombre, nivel) {
    override fun atacar() {
        println("$nombre lanza el hechizo $hechizo")
    }

    fun lanzarEncantamiento() {
        println("$nombre lanza un encantamiento mágico")
    }
}

fun main() {
    val guerrero = Guerrero("Conan", 10, "Espada de fuego")
    guerrero.atacar()
    guerrero.usarHabilidadEspecial()
    guerrero.subirNivel()

    println()

    val mago = Mago("Gandalf", 8, "Bola de fuego")
    mago.atacar()
    mago.lanzarEncantamiento()
    mago.subirNivel()

    println()

    val personaje=Personaje("Gaspar",5)
    personaje.atacar()
    personaje.subirNivel()
}