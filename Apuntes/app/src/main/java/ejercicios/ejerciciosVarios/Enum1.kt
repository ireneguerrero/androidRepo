package ejerciciosVarios

enum class DiasDeLaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}

fun main() {
    //Crear una variable que represente el día actual
    val diaActual = DiasDeLaSemana.MIERCOLES

    when (diaActual) {
        DiasDeLaSemana.LUNES -> println("¡Es lunes!")
        DiasDeLaSemana.MARTES -> println("¡Es martes!")
        DiasDeLaSemana.MIERCOLES -> println("¡Es miércoles!")
        DiasDeLaSemana.JUEVES -> println("¡Es jueves!")
        DiasDeLaSemana.VIERNES -> println("¡Es viernes!")
        DiasDeLaSemana.SABADO -> println("¡Es sábado!")
        DiasDeLaSemana.DOMINGO -> println("¡Es domingo!")
    }
}