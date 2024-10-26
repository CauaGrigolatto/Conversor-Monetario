package br.edu.ifsp.conversormonetario

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), OnClickListener {
    private val DOLAR_VALUE = 4.5

    private lateinit var input: EditText
    private lateinit var toRealBtn: Button
    private lateinit var toDolarBtn: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.input = findViewById(R.id.valueInput)
        this.toRealBtn = findViewById(R.id.toRealBtn)
        this.toDolarBtn = findViewById(R.id.toDolarBtn)
        this.resultText = findViewById(R.id.resultText)

        this.toRealBtn.setOnClickListener(this)
        this.toDolarBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val value = input.getText().toString().toDoubleOrNull() ?: 0.0
        var resultMessage = String()

        if (v == toDolarBtn) {
            val valueInDolar = convertToDolar(value)
            resultMessage = "U$ ${valueInDolar}"
        }
        else if (v == toRealBtn) {
            val valueInReal = convertToReal(value);
            resultMessage = "R$ ${valueInReal}"
        }

        resultText.text = resultMessage
    }

    private fun convertToDolar(value: Double): Double {
        return value / DOLAR_VALUE
    }

    private fun convertToReal(value: Double): Double {
        return value * DOLAR_VALUE
    }
}