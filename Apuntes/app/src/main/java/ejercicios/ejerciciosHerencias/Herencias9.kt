package ejerciciosHerencias

open class Producto1(val nombre: String, val precio: Double) {
    open fun mostrarDetalle() {
        println("El producto $nombre tiene como precio $precio")
    }
}

open class Envio(nombre: String, precio: Double, val destino: String) : Producto1(nombre, precio) {
    fun calcularCostoEnvio() {
        val devolucion = precio * 0.1
        val precioFinal = devolucion + precio
        println("La devolución del precio del producto es de $devolucion y el precio final es: $precioFinal")
    }

    override fun mostrarDetalle() {
        println("El producto $nombre con el precio $precio tiene como destino $destino")
    }
}

class Factura(nombre: String, precio: Double, destino: String, val numeroFactura: String) :
    Envio(nombre, precio, destino) {
    fun imprimirFactura() {
        println("El producto $nombre con el precio $precio tiene como destino $destino y su número de factura es $numeroFactura")
    }
}

fun main() {
    val factura = Factura("Laptop", 1200.0, "Ciudad A", "F2023001")
    factura.imprimirFactura()
}