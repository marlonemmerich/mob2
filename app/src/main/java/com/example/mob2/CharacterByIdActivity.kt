package com.example.mob2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterByIdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_by_id)

        val edtId = findViewById<EditText>(R.id.edtId)
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnBuscar.setOnClickListener {
            val id = edtId.text.toString()

            if (id.isEmpty()) {
                Toast.makeText(this, "Digite um ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tvResultado.text = "Buscando..."

            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    val characters = withContext(Dispatchers.IO) {
                        RetrofitClient.apiService.getCharacterById(id)
                    }

                    if (characters.isNotEmpty()) {
                        val character = characters[0]
                        tvResultado.text = "Nome: ${character.name}\nCasa: ${character.house}"
                    } else {
                        tvResultado.text = "Personagem não encontrado"
                    }

                } catch (e: Exception) {
                    tvResultado.text = "Erro: ${e.message}"
                }
            }
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
