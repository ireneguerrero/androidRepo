package simulacroExamen

fun main() {
    val matriz = arrayOf(
        intArrayOf(1, 2, 3),
        arrayOf("Alberto", 78, "Laura"),
        floatArrayOf(700.21f, 800.23f, 900.24f)
    )

    val nombres = matriz[1] as Array<Any>

    println("Nombres: ${nombres.joinToString(", ")}")

    val importes = matriz[2] as FloatArray
    val sumaImportes = importes.sum()
    val mediaImportes = importes.average()
    println("Suma de importes: $sumaImportes")
    println("Media de importes: $mediaImportes")
}