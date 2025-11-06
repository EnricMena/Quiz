package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correct = intent.getIntExtra("correct", 0)
        val incorrect = intent.getIntExtra("incorrect", 0)

        val txtCorrect: TextView = findViewById(R.id.txtCorrect)
        val txtIncorrect: TextView = findViewById(R.id.txtIncorrect)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        txtCorrect.text = "Aciertos: $correct"
        txtIncorrect.text = "Fallos: $incorrect"

        btnRestart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}

