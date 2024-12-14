package ru.borisov.hiddencalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var outputFieldTV: TextView
    lateinit var openCalcBTN: Button
    private val calculatorLayout =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let {
                    val result = it.getStringExtra("result")
                    outputFieldTV.text = result
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        outputFieldTV = findViewById(R.id.outputFieldTV)
        openCalcBTN = findViewById(R.id.openCalcBTN)
        openCalcBTN.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            calculatorLayout.launch(intent)
        }
    }
}