package com.example.mob2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StaffActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        // Carrega automaticamente ao abrir a tela
        tvResultado.text = "Carregando..."

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val staff = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getStaff()
                }

                if (staff.isNotEmpty()) {
                    val resultado = StringBuilder()
                    resultado.append("Professores:\n\n")
                    staff.forEach { character ->
                        resultado.append("${character.name}\n")
                    }
                    tvResultado.text = resultado.toString()
                } else {
                    tvResultado.text = "Nenhum professor encontrado"
                }

            } catch (e: Exception) {
                tvResultado.text = "Erro: ${e.message}"
            }
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
