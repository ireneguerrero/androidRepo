package simulacroExamen

// Interfaz para el desplazamiento
interface Desplazable {
    fun desplazar(unidades: Int)
}

// Interfaz para hacer sonidos
interface HacerSonido {
    fun hacerSonido()
}

interface Aparcar {
    fun aparcar()
}

// Clase Coche que implementa Desplazable y HacerSonido
class Coche(val modelo: String) : Desplazable, HacerSonido, Aparcar {
    override fun desplazar(unidades: Int) {
        println("El coche $modelo se ha desplazado $unidades veces")
    }

    override fun hacerSonido() {
        println("El coche $modelo hace el sonido del claxon")
    }

    override fun aparcar() {
        println("El coche $modelo ha aparcado")
    }
}

class Moto : Desplazable, HacerSonido, Aparcar {
    override fun desplazar(unidades: Int) {
        println("La moto se ha desplazado $unidades veces")
    }

    override fun hacerSonido() {
        println("La moto hace un sonido característico")
    }

    override fun aparcar() {
        println("Aparcao")
    }
}

// Clase Bicicleta que implementa Desplazable y HacerSonido
class Bicicleta : Desplazable, HacerSonido {
    override fun desplazar(unidades: Int) {
        println("La bicicleta se ha desplazado $unidades veces")
    }

    override fun hacerSonido() {
        println("La bicicleta hace un sonido característico")
    }
}

fun main() {
    val coche = Coche("Toyota")
    val bicicleta = Bicicleta()
    val moto = Moto()

    coche.desplazar(50)
    coche.hacerSonido()
    coche.aparcar()
    println()
    moto.desplazar(35)
    moto.hacerSonido()
    moto.aparcar()
    println()
    bicicleta.desplazar(20)
    bicicleta.hacerSonido()
}