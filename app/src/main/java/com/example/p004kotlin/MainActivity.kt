package com.example.p004kotlin

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.view.View
import android.widget.Toast
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.content.DialogInterface

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputUser = findViewById<EditText>(R.id.inputUser)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        // Boton para ingresar
        btnLogin.setOnClickListener {
            val nombre = inputUser.text.toString().trim()

            if (nombre.isNotEmpty()) {
                Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, ReciboNominaActivity::class.java)
                intent.putExtra("nombre", nombre)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Por favor, ingrese un nombre de usuario", Toast.LENGTH_SHORT).show()
            }
        }

        btnSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de querer salir?")
            builder.setPositiveButton("Sí") { dialog, which ->
                // Acciones a realizar si se selecciona "Sí"
                finishAffinity() // Cierra todas las actividades y sale de la aplicación
            }
            builder.setNegativeButton("No") { dialog, which ->
                // Acciones a realizar si se selecciona "No"
                dialog.dismiss() // Cierra el diálogo sin realizar ninguna acción adicional
            }
            val dialog = builder.create()
            dialog.show()
        }

    }
}
