package ejerciciosArrays

fun main() {

    //EJERCICIO PAÍSES
    val arrayPaises1 = arrayOfNulls<String>(3)

    arrayPaises1[0] = "Alemania"
    arrayPaises1[1] = "Francia"
    arrayPaises1[2] = "Italia"

    val arrayPaises2 = arrayPaises1.copyOf(arrayPaises1.size + 2)
    arrayPaises2[arrayPaises1.size] = "España"
    arrayPaises2[arrayPaises1.size + 1] = "Grecia"

    println("Array1: ${arrayPaises1.joinToString(", ")}")
    println("Array2: ${arrayPaises2.joinToString(", ")}")

    //EJERCICIO EMPRESAS
    val arrayEmpresas1 = arrayOfNulls<String>(3)
    arrayEmpresas1[0] = "Microsoft"
    arrayEmpresas1[1] = "Meta"
    arrayEmpresas1[2] = "Apple"

    val arrayEmpresas2 = arrayEmpresas1.copyOf(arrayEmpresas1.size + 5)
    arrayEmpresas2[arrayEmpresas1.size] = "SAMSUNG"
    arrayEmpresas2[arrayEmpresas1.size + 1] = "LENOVO"
    arrayEmpresas2[arrayEmpresas1.size + 2] = "XIAOMI"
    arrayEmpresas2[arrayEmpresas1.size + 3] = "INTEL"
    arrayEmpresas2[arrayEmpresas1.size + 4] = "AMD"

    println("Array1: ${arrayEmpresas1.joinToString(", ")}")
    println("Array2: ${arrayEmpresas2.joinToString(", ")}")

    //EJERCICIO CON STRING E INT
    val arrayMultitipo1 = arrayOf(1, "table", 2, "Monitor")

    val arrayMultitipo2 = arrayMultitipo1.copyOf(arrayMultitipo1.size + 4)
    arrayMultitipo2[4] = 3
    arrayMultitipo2[5] = "Portatil"
    arrayMultitipo2[6] = 4
    arrayMultitipo2[7] = "Móvil"

    println("Array Multitipo 1: ${arrayMultitipo1.joinToString(", ")}")
    println("Array Multitipo 2: ${arrayMultitipo2.joinToString(", ")}")

}