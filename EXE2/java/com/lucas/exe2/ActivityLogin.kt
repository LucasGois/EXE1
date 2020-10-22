package com.lucas.exe2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ActivityLogin : AppCompatActivity() {

    private lateinit var tf_login: EditText
    private lateinit var tf_senha: EditText
    private lateinit var btn_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tf_login = findViewById(R.id.tf_login)
        tf_senha = findViewById(R.id.tf_senha)
        btn_login = findViewById(R.id.btn_login)

        btn_login.setOnClickListener { login() }
    }

    private fun login() {
        if (tf_login.text.toString() == "adm" && tf_senha.text.toString() == "123") {
            startActivity(Intent(this, ActivityTela::class.java))
        }
    }
}