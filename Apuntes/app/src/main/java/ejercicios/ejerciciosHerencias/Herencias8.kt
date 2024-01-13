package ejerciciosHerencias

open class Empresa(val nombre: String, val ubicacion: String) {
    fun realizarOperacionesGenerales() {
        println("La empresa $nombre tiene como ubicación: $ubicacion")
    }
}

class Empleado(nombre: String, ubicacion: String, val cargo: String) : Empresa(nombre, ubicacion) {
    fun trabajar() {
        println("El empleado $nombre con el cargo $cargo está trabajando")
    }
}

class Producto(nombre: String, ubicacion: String, val categoria: String) :
    Empresa(nombre, ubicacion) {
    fun producir() {
        println("El producto $nombre de categoría $categoria está en producción")
    }
}

class Cliente1(nombre: String, ubicacion: String, val tipo: String) : Empresa(nombre, ubicacion) {
    fun comprar() {
        println("El cliente $nombre del tipo $tipo está realizando una compra")
    }
}

fun main() {
    val empresa = Empresa("Cenec", "Calle a")
    empresa.realizarOperacionesGenerales()
    println()
    val empleado = Empleado("Laura Delgado", "Calle a", "Desarrollador")
    empleado.trabajar()
    println()
    val producto = Producto("Portatil HP OMNIO", "Calle a", "Electrónica")
    producto.producir()
    println()
    val cliente = Cliente1("Microsoft", "Calle a", "Empresarial")
    cliente.comprar()
}