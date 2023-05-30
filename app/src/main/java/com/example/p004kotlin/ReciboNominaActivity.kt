package com.example.p004kotlin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.widget.Toast
import android.content.Intent
import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.RadioButton
import java.util.Random
import android.text.Editable


class ReciboNominaActivity : AppCompatActivity() {
    private lateinit var recibo: ReciboNomina
    private lateinit var txtRecibo: TextView
    private lateinit var txtNombre: TextView

    private lateinit var lblMostrarNombreUser: TextView

    private lateinit var txtHorasNormal: EditText

    private lateinit var txtHorasExtras: EditText
    private lateinit var rdbPuesto1: RadioButton
    private lateinit var rdbPuesto2: RadioButton
    private lateinit var rdbPuesto3: RadioButton
    private lateinit var lblImpuestoPor: TextView
    private lateinit var lblSubtotal: TextView
    private lateinit var lblImpuesto: TextView
    private lateinit var lblTotal: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button

    private lateinit var random: Random


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recibonomina)

        // Inicializar los componentes de la interfaz

        lblMostrarNombreUser = findViewById(R.id.lblMostrarNombreUser)
        txtHorasNormal = findViewById(R.id.txtHorasNormal)
        txtRecibo = findViewById(R.id.txtRecibo)
        txtRecibo.isEnabled = false // Deshabilitar la edición del campo de texto del número de recibo
        txtNombre = findViewById(R.id.txtNombre)
        txtHorasExtras = findViewById(R.id.txtHorasExtras)
        rdbPuesto1 = findViewById(R.id.rdbPuesto1)
        rdbPuesto2 = findViewById(R.id.rdbPuesto2)
        rdbPuesto3 = findViewById(R.id.rdbPuesto3)
        lblSubtotal = findViewById(R.id.lblSubtotal)
        lblImpuesto = findViewById(R.id.lblImpuesto)
        lblTotal = findViewById(R.id.lblTotal)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnSalir = findViewById(R.id.btnSalir)

        val nombre = intent.getStringExtra("nombre")

        lblMostrarNombreUser.text = nombre

        random = Random()

        // Establecer el número de recibo automáticamente
        val numReciboInt = generarNumeroReciboAleatorio(5, 10)
        txtRecibo.text = numReciboInt.toString()

        // Asignar acciones a los botones
        btnCalcular.setOnClickListener {
            calcularReciboNomina()
        }





    private fun calcularReciboNomina() {
        // Obtener los valores de los componentes
        val numRecibo = txtRecibo.text.toString()
        val nombre = txtNombre.text.toString()
        val horasNormal = txtHorasNormal.text.toString()
        val horasExtras = txtHorasExtras.text.toString()
        val puesto = obtenerPuestoSeleccionado()

        // Validar que todos los campos estén llenos y el puesto esté seleccionado
        if (TextUtils.isEmpty(numRecibo) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(horasNormal)
            || TextUtils.isEmpty(horasExtras) || puesto == 0) {
            Toast.makeText(this@ReciboNominaActivity, "Llene todos los campos y seleccione un puesto.", Toast.LENGTH_SHORT).show()
            return // Salir del método si no se cumplen las condiciones
        }

        // Convertir los valores a los tipos de datos correspondientes
        val numReciboInt = numRecibo.toInt()
        val horasTrabNormal = horasNormal.toInt()
        val horasTrabExtras = horasExtras.toInt()

        // Crear una instancia de ReciboNomina con los valores obtenidos
        recibo = ReciboNomina(numReciboInt, nombre, horasTrabNormal, horasTrabExtras, puesto)

        // Calcular los valores y mostrarlos en los TextView correspondientes
        val subtotal = recibo.calcularSubtotal()
        val impuesto = recibo.calcularImpuesto()
        val total = recibo.calcularTotal()

        lblSubtotal.text = subtotal.toString()
        lblImpuesto.text = impuesto.toString()
        lblTotal.text = total.toString()
    }

    private fun obtenerPuestoSeleccionado(): Int {
        return when {
            rdbPuesto1.isChecked -> 1
            rdbPuesto2.isChecked -> 2
            rdbPuesto3.isChecked -> 3
            else -> 0 // Valor por defecto en caso de que no se seleccione ningún puesto
        }
    }

    private fun generarNumeroReciboAleatorio(minDigits: Int, maxDigits: Int): Int {
        val min = Math.pow(10.0, (minDigits - 1).toDouble()).toInt()
        val max = Math.pow(10.0, maxDigits.toDouble()).toInt() - 1
        return random.nextInt(max - min + 1) + min
    }
}
