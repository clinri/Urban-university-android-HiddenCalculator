package ru.borisov.hiddencalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var fieldOneET: EditText
    lateinit var fieldTwoET: EditText
    lateinit var plusBTN: Button
    lateinit var minusBTN: Button
    lateinit var multiBTN: Button
    lateinit var devBTN: Button
    lateinit var resultTV: TextView
    lateinit var transferResultBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        initFields()
        setClickListeners()
    }

    private fun setClickListeners() {
        plusBTN.setOnClickListener(this)
        minusBTN.setOnClickListener(this)
        multiBTN.setOnClickListener(this)
        devBTN.setOnClickListener(this)
        transferResultBTN.setOnClickListener(this)
    }

    private fun initFields() {
        fieldOneET = findViewById(R.id.fieldOneET)
        fieldTwoET = findViewById(R.id.fieldTwoET)
        plusBTN = findViewById(R.id.plusBTN)
        minusBTN = findViewById(R.id.minusBTN)
        multiBTN = findViewById(R.id.multiBTN)
        devBTN = findViewById(R.id.devBTN)
        resultTV = findViewById(R.id.resultTV)
        transferResultBTN = findViewById(R.id.transferResultBTN)
    }

    override fun onClick(v: View?) {
        v?.id?.let { id ->
            if (
                id in listOf(R.id.plusBTN, R.id.minusBTN, R.id.multiBTN, R.id.devBTN) &&
                fieldOneET.text.isNotEmpty() &&
                fieldTwoET.text.isNotEmpty()
            ) {
                val fieldOne = fieldOneET.text.toString().toInt()
                val fieldTwo = fieldTwoET.text.toString().toInt()
                val result = when (id) {
                    R.id.plusBTN -> fieldOne + fieldTwo
                    R.id.minusBTN -> fieldOne - fieldTwo
                    R.id.multiBTN -> fieldOne * fieldTwo
                    R.id.devBTN -> fieldOne / fieldTwo
                    else -> 0
                }
                resultTV.text = String.format("%s", result)
                return
            }
            if (id == R.id.transferResultBTN && resultTV.text.isNotEmpty()) {
                setResult(
                    /* resultCode = */ RESULT_OK,
                    /* data = */ Intent().apply { putExtra("result", resultTV.text.toString()) }
                )
                finish()
            }
        }
    }
}