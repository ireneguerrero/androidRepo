package com.examenfinal

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LibrosHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Biblioteca.db"
        private const val TABLE_NAME = "libros"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_AUTOR = "autor"
        private const val COLUMN_GENERO = "genero"
        private const val COLUMN_EDITORIAL = "editorial"
        private const val COLUMN_ANIO = "anio"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY =
            ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_TITULO TEXT, $COLUMN_AUTOR TEXT, $COLUMN_GENERO TEXT, "
                    + "$COLUMN_EDITORIAL TEXT, $COLUMN_ANIO INTEGER)")
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addLibro(libro: Libro): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITULO, libro.titulo)
            put(COLUMN_AUTOR, libro.autor)
            put(COLUMN_GENERO, libro.genero)
            put(COLUMN_EDITORIAL, libro.editorial)
            put(COLUMN_ANIO, libro.anio)
        }
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    @SuppressLint("Range")
    fun getAllLibros(): List<Libro> {
        val librosList = mutableListOf<Libro>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val libro = Libro(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TITULO)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_AUTOR)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_GENERO)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EDITORIAL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIO))
                )
                librosList.add(libro)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return librosList
    }

    fun deleteAllLibros() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
        db.close()
    }

}