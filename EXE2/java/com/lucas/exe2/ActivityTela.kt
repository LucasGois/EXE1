package com.lucas.exe2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog

class ActivityTela : AppCompatActivity() {

    private lateinit var tf_consumo_total: EditText
    private lateinit var tf_couvert_por_pessoa: EditText
    private lateinit var rb_taxa_servico_sim: RadioButton
    private lateinit var rb_taxa_servico_nao: RadioButton
    private lateinit var rb_dividir_pessoas_2: RadioButton
    private lateinit var rb_dividir_pessoas_3: RadioButton
    private lateinit var rb_dividir_pessoas_4: RadioButton
    private lateinit var rb_dividir_pessoas_5: RadioButton
    private lateinit var btn_dividir_conta: Button
    private lateinit var tf_custo_total: EditText
    private lateinit var tf_valor_por_pessoa: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela)

        tf_consumo_total = findViewById(R.id.tf_consumo_total)
        tf_couvert_por_pessoa = findViewById(R.id.tf_couvert_por_pessoa)
        rb_taxa_servico_sim = findViewById(R.id.rb_taxa_servico_sim)
        rb_taxa_servico_nao = findViewById(R.id.rb_taxa_servico_nao)
        rb_dividir_pessoas_2 = findViewById(R.id.rb_dividir_pessoas_2)
        rb_dividir_pessoas_3 = findViewById(R.id.rb_dividir_pessoas_3)
        rb_dividir_pessoas_4 = findViewById(R.id.rb_dividir_pessoas_4)
        rb_dividir_pessoas_5 = findViewById(R.id.rb_dividir_pessoas_5)
        btn_dividir_conta = findViewById(R.id.btn_dividir_conta)
        tf_custo_total = findViewById(R.id.tf_custo_total)
        tf_valor_por_pessoa = findViewById(R.id.tf_valor_por_pessoa)

        rb_taxa_servico_nao.isChecked = true
        rb_dividir_pessoas_2.isChecked = true

        btn_dividir_conta.setOnClickListener { calcular() }

        tf_custo_total.isEnabled = false
        tf_valor_por_pessoa.isEnabled = false
    }

    private fun mensagem(titulo: String, mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensagem)
            .setNeutralButton("Ok", null)
            .show()
    }

    private fun calcular() {
        val consumoTotal: Double
        val couvertPorPessoa: Double

        try {
            consumoTotal = tf_consumo_total.text.toString().toDouble()
        } catch (ex: Exception) {
            mensagem("Erro", "Consumo Total inválido!")
            return
        }
        try {
            couvertPorPessoa = tf_couvert_por_pessoa.text.toString().toDouble()
        } catch (ex: Exception) {
            mensagem("Erro", "Couvert por Pessoa inválido!")
            return
        }

        var custoTotal = consumoTotal + couvertPorPessoa

        if (rb_taxa_servico_sim.isChecked) custoTotal *= 1.1

        var valorPorPessoa = when {
            rb_dividir_pessoas_2.isChecked -> 2.0
            rb_dividir_pessoas_3.isChecked -> 3.0
            rb_dividir_pessoas_4.isChecked -> 4.0
            rb_dividir_pessoas_5.isChecked -> 5.0
            else -> 1.0
        }

        valorPorPessoa = custoTotal / valorPorPessoa

        tf_custo_total.setText("%.2f".format(custoTotal))
        tf_valor_por_pessoa.setText("%.2f".format(valorPorPessoa))
    }
}