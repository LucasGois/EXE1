package com.lucas

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var et_primeiro_numero: EditText
    private lateinit var et_segundo_numero: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_primeiro_numero = findViewById(R.id.et_primeiro_numero)
        et_segundo_numero = findViewById(R.id.et_segundo_numero)

        findViewById<Button>(R.id.btn_somar).setOnClickListener { handleClick(1) }
        findViewById<Button>(R.id.btn_subtrair).setOnClickListener { handleClick(2) }
        findViewById<Button>(R.id.btn_multiplicar).setOnClickListener { handleClick(3) }
        findViewById<Button>(R.id.btn_dividir).setOnClickListener { handleClick(4) }
    }

    private fun handleClick(type: Int) {
        var alert = AlertDialog.Builder(this).setTitle("Aviso")
            .setPositiveButton("Ok") { dialogInterface, _ -> dialogInterface.cancel() }
        val num1: Double
        val num2: Double

        try {
            num1 = et_primeiro_numero.text.toString().toDouble()
        } catch (ex: Exception) {
            alert.setMessage("Primeiro número inválido!").show()
            return
        }

        try {
            num2 = et_segundo_numero.text.toString().toDouble()
        } catch (ex: Exception) {
            alert.setMessage("Segundo número inválido!").show()
            return
        }

        val result = "O resultado da " + when (type) {
            1 -> "soma é: ${num1 + num2}"
            2 -> "subtração é: ${num1 - num2}"
            3 -> "multiplicação é: ${num1 * num2}"
            4 -> "divisão é: ${num1 / num2}"
            else -> 0
        }

        alert = if (type == 4 && num2 == 0.0) {
            alert.setMessage("Não é possível dividir por zero!")
        } else {
            alert.setTitle("Resultado:").setMessage(result)
        }
        alert.show()
    }
}