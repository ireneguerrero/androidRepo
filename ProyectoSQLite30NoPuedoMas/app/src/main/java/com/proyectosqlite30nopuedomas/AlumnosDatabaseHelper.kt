package com.proyectosqlite30nopuedomas
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AlumnosDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Alumnos.db"
        private const val TABLE_NAME = "alumnos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "nombre"
        private const val COLUMN_LAST_NAME = "apellidos"
        private const val COLUMN_DNI = "dni"
        private const val COLUMN_AGE = "edad"
        private const val COLUMN_COURSE = "curso"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, $COLUMN_LAST_NAME TEXT, $COLUMN_DNI TEXT, "
                + "$COLUMN_AGE INTEGER, $COLUMN_COURSE TEXT)")
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addAlumno(nombre: String, apellidos: String, dni: String, edad: Int, curso: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, nombre)
            put(COLUMN_LAST_NAME, apellidos)
            put(COLUMN_DNI, dni)
            put(COLUMN_AGE, edad)
            put(COLUMN_COURSE, curso)
        }
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getAllAlumnos(): List<Alumno> {
        val alumnosList = mutableListOf<Alumno>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val apellidos = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME))
                val dni = cursor.getString(cursor.getColumnIndex(COLUMN_DNI))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
                val curso = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE))

                val alumno = Alumno(id, nombre, apellidos, dni, edad, curso)
                alumnosList.add(alumno)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return alumnosList
    }
}

data class Alumno(
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val dni: String,
    val edad: Int,
    val curso: String
)

fun main() {
    val context: Context = TODO() // Debes proporcionar el contexto adecuado de tu aplicación
    val dbHelper = AlumnosDatabaseHelper(context)

    // Aquí puedes llamar a los métodos para agregar alumnos y obtener todos los alumnos
    // Por ejemplo:
    val id = dbHelper.addAlumno("Juan", "Perez", "12345678A", 25, "1A")
    println("Alumno agregado con ID: $id")

    val alumnos = dbHelper.getAllAlumnos()
    alumnos.forEach {
        println("${it.nombre} ${it.apellidos}, DNI: ${it.dni}, Edad: ${it.edad}, Curso: ${it.curso}")
    }
}
