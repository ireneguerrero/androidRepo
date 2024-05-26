package com.ghostly

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private val google_sign_in = 100
    private lateinit var btnRegistro: Button
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etContra: EditText
    private lateinit var ibGoogle: ImageButton
    private lateinit var loginLY: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ghostly)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegistro = findViewById(R.id.btnRegistro)
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmail)
        etContra = findViewById(R.id.etContra)
        ibGoogle = findViewById(R.id.ibGoogle)
        loginLY = findViewById(R.id.loginLY)

        setup()
        session()
    }

    private fun setup() {

        btnRegistro.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etContra.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    etEmail.text.toString(),
                    etContra.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("email", it.result?.user?.email ?: "")
                        startActivity(intent)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        btnLogin.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etContra.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etEmail.text.toString(), etContra.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("email", it.result?.user?.email ?: "")
                            startActivity(intent)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        ibGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, google_sign_in)
        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuairo")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)

        if (email != null) {
            loginLY.visibility = View.INVISIBLE
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == google_sign_in) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("email", account.email ?: "")
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Fallo al crear usuario", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
            } catch (e: ApiException) {
                showAlert()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        loginLY.visibility = View.VISIBLE
    }

}