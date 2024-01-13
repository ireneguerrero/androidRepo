package ejerciciosHerencias

open class ProductoInformatico(val nombre: String, val precio: Int) {
    fun encender() {
        println("$nombre se ha encendido")
    }

    fun apagar() {
        println("$nombre se ha apagado")
    }

    open fun ejecutar() {
        println("$nombre se está ejecutando")
    }
}

class Laptop(nombre: String, precio: Int, val marca: String) : ProductoInformatico(nombre, precio) {
    override fun ejecutar() {
        println("$nombre de la marca $marca se está ejecutando")
    }
}

class Impresora(nombre: String, precio: Int, val tipo: String) :
    ProductoInformatico(nombre, precio) {
    fun imprimir() {
        println("$nombre está imprimiendo y el tipo de impresora es: $tipo")
    }
}

fun main() {
    val laptop = Laptop("HP Envy", 200, "HP")
    laptop.encender()
    laptop.ejecutar()
    laptop.apagar()
    println()
    val impresora = Impresora("Epson EcoTank", 400, "Inyección de tinta")
    impresora.encender()
    impresora.ejecutar()
    impresora.imprimir()
    impresora.apagar()
}