package ejerciciosInterfaces

interface Persona {
    val nombre: String
    val edad: Int

    fun presentar()
}

class Alumno(
    override val nombre: String,
    override val edad: Int,
    val grado: String
) : Persona {
    override fun presentar() {
        println("Soy un alumno del grado $grado")
    }

    fun estudiar() {
        println("Estudiando para el próximo examen")
    }
}

class Profesor(
    override val nombre: String,
    override val edad: Int,
    val asignatura: String
) : Persona {
    override fun presentar() {
        println("Soy profesor de la asignatura $asignatura")
    }

    fun impartir() {
        println("Impartiendo la lección del día")
    }
}

fun main(){
    val alumno=Alumno("Juan",15,"1º FP")
    println("Hola, mi nombre es ${alumno.nombre} y tengo ${alumno.edad} años")
    alumno.presentar()
    alumno.estudiar()

    println()

    val profesor=Profesor("Prof. Martinez",25,"Entornos de desarrollo")
    println("Hola, mi nombre es ${profesor.nombre} y tengo ${profesor.edad} años")
    profesor.presentar()
    profesor.impartir()
}