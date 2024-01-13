package POO

class Cliente(nombre: String, apellido: String, tipo: String, ingresos: Double) {
    constructor(nombre: String, apellido: String, tipo: String) : this(nombre, apellido, tipo, 0.0)

    var nombre: String = nombre
    var apellido: String = apellido
    var tipo: String = tipo
    var ingresos: Double = ingresos

    fun imprimir() {
        println("El cliente $nombre $apellido que es $tipo tiene unos ingresos de $ingresos")
    }

    fun tipo() {
        if (tipo == "Empresa") {
            println("Le atiende la oficina central")
        } else {
            println("Le atiende la oficina local")
        }
    }
}

fun main() {
    val cliente1 = Cliente("Juan", "Pérez", "Empresa", 120000.00)
    cliente1.imprimir()
    cliente1.tipo()

    val cliente2 = Cliente("María", "Fernández", "Particular")
    cliente2.imprimir()
    cliente2.tipo()
}