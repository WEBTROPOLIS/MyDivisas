package com.example.mydivisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.mydivisas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val currencies = arrayOf("USD", "EUR", "JPY", "GBP", "CAD")
    private val exchangeRates = doubleArrayOf(1.0, 0.85, 110.92, 0.72, 1.25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFromCurrency.adapter = currencyAdapter
        binding.spinnerToCurrency.adapter = currencyAdapter

        binding.btnConvert.setOnClickListener {
            convertCurrency()
        }

        binding.btnReset.setOnClickListener {
            resetFields()
        }
    }

    private fun convertCurrency() {
        val amount = binding.etAmount.text.toString().toDoubleOrNull()
        if (amount != null) {
            val fromCurrency = binding.spinnerFromCurrency.selectedItemPosition
            val toCurrency = binding.spinnerToCurrency.selectedItemPosition
            val convertedAmount = amount * exchangeRates[fromCurrency] / exchangeRates[toCurrency]
            binding.tvResult.text = String.format("%.2f", convertedAmount)
        } else {
            binding.tvResult.text = "Ingrese un monto válido"
        }
    }

    private fun resetFields() {
        binding.etAmount.text.clear()
        binding.spinnerFromCurrency.setSelection(0)
        binding.spinnerToCurrency.setSelection(0)
        binding.tvResult.text = ""
    }
}
