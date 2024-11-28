package com.example.proyectofinal

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuración de padding con WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración de los elementos de la interfaz
        val weightSpinner: Spinner = findViewById(R.id.spinner)
        val heightSpinner: Spinner = findViewById(R.id.spinner2)
        val calculateButton: Button = findViewById(R.id.button2)
        val resetButton: Button = findViewById(R.id.button)
        val resultTextView: TextView = findViewById(R.id.textView)

        // Opciones para los Spinners desde los recursos strings.xml
        val weightOptions = resources.getStringArray(R.array.Spinner1)
        val heightOptions = resources.getStringArray(R.array.Spinner2)

        // Adaptadores para los Spinners
        weightSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weightOptions)
        heightSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, heightOptions)

        // Configuración inicial del resultado
        resultTextView.text = getString(R.string.Text1) // "Tu IMC es:"

        // Funcionalidad del botón "Calculate"
        calculateButton.setOnClickListener {
            val weight = weightSpinner.selectedItem.toString().toDouble()
            val height = heightSpinner.selectedItem.toString().toDouble()
            val bmi = calculateBMI(weight, height)
            // Muestra el texto y el número juntos
            resultTextView.text = getString(R.string.Text1) + " %.2f".format(bmi)
        }

        // Funcionalidad del botón "Reboot"
        resetButton.setOnClickListener {
            weightSpinner.setSelection(0)
            heightSpinner.setSelection(0)
            // Reinicia el texto al valor base
            resultTextView.text = getString(R.string.Text1)
        }
    }

    // Función para calcular el BMI
    private fun calculateBMI(weight: Double, height: Double): Double {
        return weight / (height * height)
    }
}
