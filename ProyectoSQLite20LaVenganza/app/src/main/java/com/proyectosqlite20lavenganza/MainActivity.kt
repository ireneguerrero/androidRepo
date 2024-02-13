import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.proyectosqlite20lavenganza.Nombre
import com.proyectosqlite20lavenganza.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnMostrar = findViewById<Button>(R.id.btnMostrar)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etContra = findViewById<EditText>(R.id.etContraseña)
        val tvMuestraNombre = findViewById<TextView>(R.id.tvMuestraNombre)

        btnAgregar.setOnClickListener {
            val dbHandler = AyudaDB(this, null)
            val nombre = etNombre.text.toString()
            val contraseña = etContra.text.toString()
            val user = Nombre(nombre, contraseña)
            dbHandler.addName(user)
            Toast.makeText(
                this,
                "Se agregó a la base de datos a: $nombre",
                Toast.LENGTH_LONG
            ).show()
            etNombre.setText("")
            etContra.setText("")
        }

        btnMostrar.setOnClickListener {
            tvMuestraNombre.text = ""
            val dbHandler = AyudaDB(this, null)
            val cursor = dbHandler.getAllName()

            if (cursor != null) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val nombre = cursor.getString(cursor.getColumnIndex(AyudaDB.COLUMN_NAME))
                    val contraseña = cursor.getString(cursor.getColumnIndex(AyudaDB.COLUMN_PASSWORD))
                    tvMuestraNombre.append("Nombre: $nombre - Contraseña: $contraseña\n")
                    cursor.moveToNext()
                }
                cursor.close()
            }
        }
    }
}
