package simulacroExamen

open class Alimento(val nombre: String, val calorias: Int) {
    open fun mostrarDetalles() {
        println("Alimento: $nombre, Calorías: $calorias")
    }
}

class Fruta(nombre: String, calorias: Int, val tipoFruta: String, val vitaminas: String) :
    Alimento(nombre, calorias) {
    override fun mostrarDetalles() {
        super.mostrarDetalles()
        println("Tipo de fruta: $tipoFruta, Vitaminas: $vitaminas")
    }
}

class Carne(nombre: String, calorias: Int, val tipoAnimal: String, val proteinas: String) :
    Alimento(nombre, calorias) {
    override fun mostrarDetalles() {
        super.mostrarDetalles()
        println("Tipo de animal: $tipoAnimal, Proteínas: $proteinas")
    }
}

class Verdura(nombre: String, calorias: Int, val tipoVerdura: String) :
    Alimento(nombre, calorias) {
    override fun mostrarDetalles() {
        super.mostrarDetalles()
        println("Tipo de verdura: $tipoVerdura")
    }
}

fun main() {
    val manzana = Fruta("Manzana", 50, "Fruta fresca", "Vitamina C")
    val pollo = Carne("Pollo", 150, "Ave", "20g de proteínas")
    val puerro = Verdura("Puerro", 30, "ejercicios")

    manzana.mostrarDetalles()
    println()
    pollo.mostrarDetalles()
    println()
    puerro.mostrarDetalles()
}