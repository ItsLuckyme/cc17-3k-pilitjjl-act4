package com.example.tipcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil
import androidx.activity.enableEdgeToEdge


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val serviceCost: EditText = findViewById(R.id.cost_of_service_edit_text)
        val tips: RadioGroup = findViewById(R.id.tip_options)
        val roundUp: Switch = findViewById(R.id.round_up_switch)
        val calculate: Button = findViewById(R.id.calculate_button)
        val tipResult: TextView = findViewById(R.id.tip_result)

        calculate.setOnClickListener {
            val costing = serviceCost.text.toString()
            val cost = costing.toDoubleOrNull()
            if (cost == null || cost == 0.0) {
                tipResult.text = getString(R.string.tip_amount, 0.0)
                return@setOnClickListener
            }
            val tipPercentage = when (tips.checkedRadioButtonId) {
                R.id.amazing_option -> 0.20
                R.id.good_option -> 0.18
                else -> 0.15
            }
            var tip = cost * tipPercentage
            if (roundUp.isChecked) {
                tip = ceil(tip)
            }
            tipResult.text = getString(R.string.tip_amount, tip)
        }
    }
}