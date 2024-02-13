package com.retosqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductosDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Productos.db"
        private const val TABLE_NAME = "productos"
        private const val COLUMN_ID_PRODUCTO = "id_producto"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_DESCRIPCION = "descripcion"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_CANTIDAD = "cantidad"
        private const val COLUMN_PROVEEDOR = "proveedor"
        private const val COLUMN_FECHA_VENCIMIENTO = "fecha_vencimiento"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY =
            ("CREATE TABLE $TABLE_NAME ($COLUMN_ID_PRODUCTO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_NOMBRE TEXT, $COLUMN_DESCRIPCION TEXT, $COLUMN_PRECIO REAL, "
                    + "$COLUMN_CANTIDAD INTEGER, $COLUMN_PROVEEDOR TEXT, $COLUMN_FECHA_VENCIMIENTO TEXT)")
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProducto(producto: Producto): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, producto.nombre)
            put(COLUMN_DESCRIPCION, producto.descripcion)
            put(COLUMN_PRECIO, producto.precio)
            put(COLUMN_CANTIDAD, producto.cantidad)
            put(COLUMN_PROVEEDOR, producto.proveedor)
            put(COLUMN_FECHA_VENCIMIENTO, producto.fecha_vencimiento)
        }
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    @SuppressLint("Range")
    fun getProducto(id: Int): Producto? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(
                COLUMN_ID_PRODUCTO, COLUMN_NOMBRE, COLUMN_DESCRIPCION, COLUMN_PRECIO,
                COLUMN_CANTIDAD, COLUMN_PROVEEDOR, COLUMN_FECHA_VENCIMIENTO
            ),
            "$COLUMN_ID_PRODUCTO=?",
            arrayOf(id.toString()), null, null, null, null
        )

        var producto: Producto? = null

        if (cursor.moveToFirst()) {
            producto = Producto(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCTO)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)),
                cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECIO)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_CANTIDAD)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PROVEEDOR)),
                cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_VENCIMIENTO))
            )
        }
        cursor.close()
        return producto
    }

    @SuppressLint("Range")
    fun getAllProductos(): List<Producto> {
        val productosList = mutableListOf<Producto>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val producto = Producto(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCTO)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECIO)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_CANTIDAD)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PROVEEDOR)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_VENCIMIENTO))
                )
                productosList.add(producto)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productosList
    }

    fun updateProducto(producto: Producto): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, producto.nombre)
            put(COLUMN_DESCRIPCION, producto.descripcion)
            put(COLUMN_PRECIO, producto.precio)
            put(COLUMN_CANTIDAD, producto.cantidad)
            put(COLUMN_PROVEEDOR, producto.proveedor)
            put(COLUMN_FECHA_VENCIMIENTO, producto.fecha_vencimiento)
        }
        val rowsAffected = db.update(
            TABLE_NAME, values, "$COLUMN_ID_PRODUCTO = ?",
            arrayOf(producto.id_producto.toString())
        )
        db.close()
        return rowsAffected
    }

    fun deleteProducto(id: Int): Int {
        val db = this.writableDatabase
        val rowsDeleted = db.delete(
            TABLE_NAME, "$COLUMN_ID_PRODUCTO = ?",
            arrayOf(id.toString())
        )
        db.close()
        return rowsDeleted
    }
}
