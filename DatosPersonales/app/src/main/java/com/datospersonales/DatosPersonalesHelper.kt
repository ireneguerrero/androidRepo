package com.datospersonales

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatosPersonalesHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Empresa.db"
        private const val TABLE_NAME = "datospersonales"
        private const val COLUMN_ID_PERSONA = "id_persona"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_APELLIDOS = "apellidos"
        private const val COLUMN_DIRECCION = "direccion"
        private const val COLUMN_CP = "cp"
        private const val COLUMN_CIUDAD = "ciudad"
        private const val COLUMN_PROVINCIA = "provincia"
        private const val COLUMN_TELEFONO = "telefono"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY =
            ("CREATE TABLE $TABLE_NAME ($COLUMN_ID_PERSONA INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_NOMBRE TEXT, $COLUMN_APELLIDOS TEXT, $COLUMN_DIRECCION TEXT, "
                    + "$COLUMN_CP TEXT, $COLUMN_CIUDAD TEXT, $COLUMN_PROVINCIA TEXT, $COLUMN_TELEFONO TEXT)")
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addDato(datoPersonal: DatoPersonal): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, datoPersonal.nombre)
            put(COLUMN_APELLIDOS, datoPersonal.apellidos)
            put(COLUMN_DIRECCION, datoPersonal.direccion)
            put(COLUMN_CP, datoPersonal.cp)
            put(COLUMN_CIUDAD, datoPersonal.ciudad)
            put(COLUMN_PROVINCIA, datoPersonal.provincia)
            put(COLUMN_TELEFONO, datoPersonal.telefono)
        }
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    @SuppressLint("Range")
    fun getDato(id: Int): DatoPersonal? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(
                COLUMN_ID_PERSONA, COLUMN_NOMBRE, COLUMN_APELLIDOS, COLUMN_DIRECCION,
                COLUMN_CP, COLUMN_CIUDAD, COLUMN_PROVINCIA, COLUMN_TELEFONO
            ),
            "$COLUMN_ID_PERSONA=?",
            arrayOf(id.toString()), null, null, null, null
        )

        var dato: DatoPersonal? = null

        if (cursor.moveToFirst()) {
            dato = DatoPersonal(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PERSONA)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDOS)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION)),
                cursor.getString(cursor.getColumnIndex(COLUMN_CP)),
                cursor.getString(cursor.getColumnIndex(COLUMN_CIUDAD)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCIA)),
                cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
            )
        }
        cursor.close()
        return dato
    }

    @SuppressLint("Range")
    fun getAllDatos(): List<DatoPersonal> {
        val datosList = mutableListOf<DatoPersonal>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val dato = DatoPersonal(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PERSONA)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDOS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CP)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CIUDAD)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCIA)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                )
                datosList.add(dato)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return datosList
    }

    fun updateDato(datoPersonal: DatoPersonal): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, datoPersonal.nombre)
            put(COLUMN_APELLIDOS, datoPersonal.apellidos)
            put(COLUMN_DIRECCION, datoPersonal.direccion)
            put(COLUMN_CP, datoPersonal.cp)
            put(COLUMN_CIUDAD, datoPersonal.ciudad)
            put(COLUMN_PROVINCIA, datoPersonal.provincia)
            put(COLUMN_TELEFONO, datoPersonal.telefono)
        }
        val rowsAffected = db.update(
            TABLE_NAME, values, "$COLUMN_ID_PERSONA = ?",
            arrayOf(datoPersonal.id_persona.toString())
        )
        db.close()
        return rowsAffected
    }

    fun deleteDato(nombre: String): Int {
        val db = this.writableDatabase
        val rowsDeleted = db.delete(
            TABLE_NAME, "$COLUMN_NOMBRE = ?",
            arrayOf(nombre.toString())
        )
        db.close()
        return rowsDeleted
    }

}