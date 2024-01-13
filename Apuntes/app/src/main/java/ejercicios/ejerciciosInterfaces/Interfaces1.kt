package ejerciciosInterfaces

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

class Movil(
    override val nombre: String,
    override val fabricante: String,
    override val precio: Double
) : ProductoElectronico {
    override fun encender() {
        println("Encendiendo el móvil $nombre fabricado por $fabricante")
    }

    override fun apagar() {
        println("Apagando el móvil $nombre")
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
    println()

    val miTelevisor2=Televisor("Tele to guapa","LG",898.45)
    println("Nombre: ${miTelevisor2.nombre}")
    println("Fabricante: ${miTelevisor2.fabricante}")
    println("Precio: ${miTelevisor2.precio}")
    miTelevisor2.encender()
    miTelevisor2.apagar()
    println()

    val miMovil = Movil("Mi Note 10", "Xiaomi", 560.95)
    println("Nombre: ${miMovil.nombre}")
    println("Fabricante: ${miMovil.fabricante}")
    println("Precio: ${miMovil.precio}")
    miMovil.encender()
    miMovil.apagar()

}