package ejerciciosBucles

fun main() {
    var notasMayoresOIgualesA7 = 0
    var notasMenoresA7 = 0

    for (i in 1..10) {
        print("Escribe la nota del alumno $i: ")
        val nota = readLine()?.toDoubleOrNull()

        if (nota != null) {
            when {
                nota >= 7 -> notasMayoresOIgualesA7++
                nota < 7 -> notasMenoresA7++
            }
        } else {
            println("Escribe una nota válida.")
            return
        }
    }

    println("Cantidad de alumnos con notas mayores o iguales a 7: $notasMayoresOIgualesA7")
    println("Cantidad de alumnos con notas menores a 7: $notasMenoresA7")
}
