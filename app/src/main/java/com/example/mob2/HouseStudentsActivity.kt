package com.example.mob2

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HouseStudentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_students)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnCarregar = findViewById<Button>(R.id.btnCarregar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCarregar.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Selecione uma casa", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val radioButton = findViewById<RadioButton>(selectedId)
            val house = radioButton.text.toString().lowercase()

            tvResultado.text = "Carregando..."

            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    val students = withContext(Dispatchers.IO) {
                        RetrofitClient.apiService.getStudentsByHouse(house)
                    }

                    if (students.isNotEmpty()) {
                        val resultado = StringBuilder()
                        resultado.append("Estudantes de ${house.capitalize()}:\n\n")
                        students.forEach { character ->
                            resultado.append("${character.name}\n")
                        }
                        tvResultado.text = resultado.toString()
                    } else {
                        tvResultado.text = "Nenhum estudante encontrado"
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