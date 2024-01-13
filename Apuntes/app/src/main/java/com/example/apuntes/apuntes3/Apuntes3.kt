package com.example.apuntes.apuntes.apuntes3

fun main() {
    // ARRAY DE VALORES ENTEROS
    val arrayValores = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    //el step 2 sirve para que se salte un valor entre medias
    for (i in 0 until arrayValores.size step 2) {
        println(arrayValores[i])
    }
    var arrayValores2 = arrayOf("A", "B", "C")
    for (i in 0 until arrayValores2.size) {
        println(arrayValores2[i])
    }
    var arrayValores3 = arrayOf("Laura", 30, "Ana", 24)
    for (i in arrayValores3.size - 1 downTo 0) {
        println(arrayValores3[i])
    }

    // ARRAY QUE CUENTA HACIA ATRÁS
    for (i in arrayValores3.size - 1 downTo 0 step 2) {
        println(arrayValores3[i])
    }

    // ARRAY VACÍO
    val arrayVacio = arrayOfNulls<String>(3)
    arrayVacio[0] = "Coín"
    arrayVacio[1] = "Cártama"
    arrayVacio[2] = "Alora"
    println(arrayVacio.joinToString())
    for (i in 0 until arrayVacio.size) {
        println(arrayVacio[i])
    }

    // MOSTRANDO DATOS DEL ARRAY USANDO GET
    println(arrayVacio.get(0))
    println(arrayVacio.get(1))
    println(arrayVacio.get(2))

    // USANDO INT
    var arrayEnteroVacio = arrayOfNulls<Int>(3)
    arrayEnteroVacio[0] = 1
    arrayEnteroVacio[1] = 2
    arrayEnteroVacio[2] = 3
    println(arrayEnteroVacio.joinToString())
    for (i in 0 until arrayEnteroVacio.size) {
        print(arrayEnteroVacio[i])
    }
    println() // lo pongo para dejar un salto de línea

    // USANDO FLOAT
    /*var arrayFloatVacio= arrayOf<Float>()
    arrayFloatVacio[0]=1.2f
    arrayFloatVacio[1]=3.2f
    arrayFloatVacio[2]=4.2f*/
    //println(arrayFloatVacio.joinToString())

    // AGRANDAR UN ARRAY
    val array1 = arrayOfNulls<String>(3)

    // COPIAR ARRAYS Y AÑADIR DATOS AL ARRAY
    array1[0] = "A"
    array1[1] = "B"
    array1[2] = "C"

    // COPIAR ARRAY1 A ARRAY2 Y AGREGAR UN ELEMENTO MÁS
    // copyOf copia el array anterior y le decimos entre paréntesis que el tamaño del array es el original +1
    val array2 = array1.copyOf(array1.size + 1)
    array2[array1.size] = "D"

    // MOSTRAR ARRAY1 Y ARRAY2 POR CONSOLA USANDO JOINTOSTRING
    println("Array1: ${array1.joinToString(", ")}")
    println("Array2: ${array2.joinToString(", ")}")

    // ARRAYS BIDIMENSIONALES
    val array2d = arrayOf(arrayOf(1, 2, 3), arrayOf("aaa", "ccc", -1))
    println(array2d.get(0))
    for (fila in array2d) {
        for (elemento in fila) {
            println(elemento)
        }
    }

    // AÑADIR VALORES USANDO SET
    var matrizEnteros = arrayOf(intArrayOf(3, 2, 1), intArrayOf(4, 5), intArrayOf(6))
    print("Valor original: " + matrizEnteros[0][2])
    matrizEnteros[0][2] = 0
    print(" valor cambiado a: " + matrizEnteros[0][2])
    matrizEnteros[0].set(2, 8)
    println(" valor cambiado con set a: " + matrizEnteros[0][2])
}