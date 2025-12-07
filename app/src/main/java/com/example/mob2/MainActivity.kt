package com.example.mob2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCharacterById = findViewById<Button>(R.id.btnCharacterById)
        val btnStaff = findViewById<Button>(R.id.btnStaff)
        val btnHouseStudents = findViewById<Button>(R.id.btnHouseStudents)
        val btnExitApp = findViewById<Button>(R.id.btnExitApp)

        btnCharacterById.setOnClickListener {
            // toDo
        }

        btnStaff.setOnClickListener {
            // toDo
        }

        btnHouseStudents.setOnClickListener {
            startActivity(Intent(this, HouseStudentsActivity::class.java))
        }

        btnExitApp.setOnClickListener {
            finishAffinity()
        }
    }
}
