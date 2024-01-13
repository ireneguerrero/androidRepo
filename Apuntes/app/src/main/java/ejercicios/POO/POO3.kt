package POO

class Notas {
    var nombre: String = ""
    var asignatura: String = ""
    var nota: Int = 0

    fun inicializar(nombre: String, asignatura: String, nota: Int) {
        this.nombre = nombre
        this.asignatura = asignatura
        this.nota = nota
    }

    fun imprimir() {
        println("El alumno $nombre tiene en la asignatura $asignatura la nota: $nota")
    }

    fun puntuacion() {
        when (nota) {
            0, 1, 2, 3, 4 -> println("Suspenso")
            5 -> println("Suficiente")
            6 -> println("Bien")
            7, 8 -> println("Notable")
            9, 10 -> println("Sobresaliente")
        }
    }
}

fun main() {
    val persona1: Notas
    persona1 = Notas()
    persona1.inicializar("Miguel", "Matemáticas", 9)
    persona1.imprimir()
    persona1.puntuacion()

    val persona2: Notas
    persona2 = Notas()
    persona2.inicializar("Carmen", "Inglés", 6)
    persona2.imprimir()
    persona2.puntuacion()

    val persona3: Notas
    persona3 = Notas()
    persona3.inicializar("Cristian", "Historia", 3)
    persona3.imprimir()
    persona3.puntuacion()

}