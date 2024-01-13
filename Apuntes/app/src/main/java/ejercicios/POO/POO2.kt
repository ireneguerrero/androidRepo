package POO

class Salario {
    lateinit var nombre: String
    var ingresos: Int = 0

    fun inicializar(nombre: String, ingresos: Int) {
        this.nombre = nombre
        this.ingresos = ingresos
    }

    fun imprimir() {
        println("Nombre: $nombre tiene unos ingresos de $ingresos")
    }

    fun esRico() {
        if (ingresos >= 2000) {
            println("Eres rico")
        } else {
            println("No está mal, podría ser mejor")
        }
    }

    fun complemento() {
        if (ingresos >= 2000 && ingresos <= 3000) {
            var incremento10 = ingresos * 0.1
            var incremento10total = incremento10 + ingresos
            var retencion1 = incremento10total * 0.15
            var retenciontotal1 = incremento10total - retencion1
            println("Complemento: $incremento10")
            println("Complemento sumado: $incremento10total")
            println("Retención: $retencion1")
            println("Complemento total: $retenciontotal1")
        } else if (ingresos > 3000) {
            var incremento20 = ingresos * 0.2
            var incremento20total = incremento20 + ingresos
            var retencion2 = incremento20total * 0.15
            var retenciontotal2 = incremento20total - retencion2
            println("Complemento: $incremento20")
            println("Complemento sumado: $incremento20total")
            println("Retención: $retencion2")
            println("Complemento total: $retenciontotal2")
        } else {
            var incremento5 = ingresos * 0.05
            var incremento5total = incremento5 + ingresos
            var retencion3 = incremento5total * 0.15
            var retenciontotal3 = incremento5total - retencion3
            println("Complemento: $incremento5")
            println("Complemento sumado: $incremento5total")
            println("Retención: $retencion3")
            println("Complemento total: $retenciontotal3")
        }
    }
}

fun main() {
    val persona1: Salario
    persona1 = Salario()
    persona1.inicializar("Cristina", 2500)
    persona1.imprimir()
    persona1.esRico()
    persona1.complemento()

    val persona2: Salario
    persona2 = Salario()
    persona2.inicializar("Pedro", 1300)
    persona2.imprimir()
    persona2.esRico()
    persona2.complemento()

}