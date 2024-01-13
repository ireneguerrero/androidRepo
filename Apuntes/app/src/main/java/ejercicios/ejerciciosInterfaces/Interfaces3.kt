package ejerciciosInterfaces

interface Trabajador{
    val nombre:String
    val edad:Int

    fun trabajar()
}

class Empleado(
    override val nombre:String,
    override val edad:Int,
    val cargo:String
):Trabajador{
    override fun trabajar(){
        println("$nombre está realizando tareas como $cargo")
    }

    fun tomarDescanso() {
        println("$nombre está tomando un descanso")
    }
}

class Jefe(
    override val nombre:String,
    override val edad:Int,
    val departamento:String
):Trabajador{
    override fun trabajar() {
        println("$nombre está supervisando el departamento de $departamento")
    }
    fun reuniones(){
        println("$nombre está llevando a cabo reuniones con el equipo")
    }
}

fun main(){
    val empleado=Empleado("Ana",25,"Desarrollador")
    println("Hola, mi nombre es ${empleado.nombre} y tengo ${empleado.edad} años")
    empleado.trabajar()
    empleado.tomarDescanso()
    println()
    val jefe=Jefe("Sr. Rodríguez",40,"Ventas")
    println("Hola, mi nombre es ${jefe.nombre} y tengo ${jefe.edad} años")
    jefe.trabajar()
    jefe.reuniones()
}