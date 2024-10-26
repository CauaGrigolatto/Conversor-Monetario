package br.edu.ifsp.conversormonetario

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var valueInput: EditText
    private lateinit var priceInput: EditText
    private lateinit var toRealBtn: Button
    private lateinit var toDolarBtn: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setComponentsById()
        setClicksHandler()
    }

    private fun setComponentsById() {
        this.valueInput = findViewById(R.id.valueInput)
        this.priceInput = findViewById(R.id.priceInput)
        this.toRealBtn = findViewById(R.id.toRealBtn)
        this.toDolarBtn = findViewById(R.id.toDolarBtn)
        this.resultText = findViewById(R.id.resultText)
    }

    private fun setClicksHandler() {
        this.toRealBtn.setOnClickListener(this)
        this.toDolarBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v == toDolarBtn) {
            displayDolarConversion()
        }
        else if (v == toRealBtn) {
            displayRealConversion()
        }
    }

    private fun displayDolarConversion() {
        val value = getValue()
        var dolarValue = convertToDolar(value)

        if (dolarValue.isNaN() || dolarValue.isInfinite()) {
            dolarValue = 0.0
        }

        display("U$ $dolarValue")
    }

    private fun displayRealConversion() {
        val value = getValue()
        val realValue = convertToReal(value)
        display("R$ $realValue")
    }

    private fun display(message: String) {
        resultText.text = message
    }

    private fun getValue(): Double {
        return valueInput.text.toString().toDoubleOrNull() ?: 0.0
    }

    private fun getPrice(): Double {
        return priceInput.text.toString().toDoubleOrNull() ?: 0.0
    }

    private fun convertToDolar(value: Double): Double {
        return value / getPrice()
    }

    private fun convertToReal(value: Double): Double {
        return value * getPrice()
    }
}