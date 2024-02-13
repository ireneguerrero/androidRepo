package com.proyectosqlite20lavenganza

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper // esta clase ayuda a gestionar la base de datos

class AyudaDB(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
//factory ayuda a gestionar los cursores, creando una fábrica de cursores que vamos a necesitar

    /*esto es un objeto, sigue el modelo de diseño singleton, permite poder usar
    los elementos, como argumentos o variables sin instanciar
    * */
    companion object {
        //creamos las constantes necesarias para la base de datos, tablas y columnas
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "miBaseDatos.db"

        /* no declaramos estas constantes como privadas porque se va a acceder a estas
        constantes desde la clase Main*/
        const val TABLE_NAME = "nombres"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "nombre"
    }

    // creamos la tabla
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE =
            ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT)")
        db.execSQL(CREATE_TABLE) //creamos la tabla
    }

    // elimina y crea de nuevo la tabla
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME") //borramos la tabla
        onCreate(db)
    }

    // añade el nombre de usuario
    fun addName(nombre: Nombre) {
        /*ContentValues es una clase proporcionada por Android que
         se utiliza para almacenar pares clave-valor, donde las claves son los
         nombres de las columnas de una tabla de base de datos y los valores son los
         datos que se insertarán en esas columnas. */
        val values = ContentValues()
        values.put(COLUMN_NAME, nombre.NombreUsuario) //añadimos el nombre
        val db = this.writableDatabase //creamos un objeto para escribir en la BBDD
        db.insert(TABLE_NAME, null, values) //lo insertamos
        db.close() //cerramos
    }

    // muestra todos los datos de la tabla
    fun getAllName(): Cursor? {
        val db = this.readableDatabase // creamos un objeto para leer
        /*En Android, rawQuery() es un método utilizado para ejecutar una consulta SQL
        en una base de datos SQLite y devolver los resultados como un cursor.
         */
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}
