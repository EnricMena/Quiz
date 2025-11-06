package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.Normalizer

class MainActivity : AppCompatActivity() {
    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var correct = 0
    private var incorrect = 0

    private lateinit var imageQuestion: ImageView
    private lateinit var textQuestion: TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageQuestion = findViewById(R.id.imageQuestion)
        textQuestion = findViewById(R.id.textQuestion)
        btn1 = findViewById(R.id.btnAnswer1)
        btn2 = findViewById(R.id.btnAnswer2)
        btn3 = findViewById(R.id.btnAnswer3)
        btn4 = findViewById(R.id.btnAnswer4)

        questions = listOf(
            Question(
                "¿Cuál es la capital de España?",
                listOf("Madrid", "Barcelona", "Valencia", "Sevilla"),
                0,
                "espana"
            ),
            Question(
                "¿Cuánto es 2 + 2?",
                listOf("3", "4", "5", "6"),
                1,
                "suma"
            ),
            Question(
                "¿Qué planeta es conocido como el planeta rojo?",
                listOf("Marte", "Tierra", "Venus", "Júpiter"),
                0,
                "planeta"
            )
        )

        showQuestion()
    }

    private fun normalizeName(name: String): String {
        // Remove extension if present
        var base = name.substringBeforeLast('.')
        // Normalize accents (e.g., ñ -> n, á -> a)
        base = Normalizer.normalize(base, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
        // Replace non allowed chars with underscore and lowercase
        base = base.replace("[^a-zA-Z0-9_]".toRegex(), "_").toLowerCase()
        return base
    }

    private fun resolveDrawableId(name: String): Int {
        // Try direct
        var candidate = normalizeName(name)
        var resId = resources.getIdentifier(candidate, "drawable", packageName)
        if (resId != 0) return resId
        // Try some common extensions removed (already removed) and fallbacks
        val alternatives = listOf(name, candidate)
        for (alt in alternatives) {
            val n = normalizeName(alt)
            resId = resources.getIdentifier(n, "drawable", packageName)
            if (resId != 0) return resId
        }
        // Fallback to a built-in icon if present
        resId = resources.getIdentifier("ic_q1", "drawable", packageName)
        return resId
    }

    private fun showQuestion() {
        if (currentIndex >= questions.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("correct", correct)
            intent.putExtra("incorrect", incorrect)
            startActivity(intent)
            finish()
            return
        }
        val q = questions[currentIndex]
        val imgRes = resolveDrawableId(q.imageName)
        if (imgRes != 0) {
            imageQuestion.setImageResource(imgRes)
            imageQuestion.visibility = View.VISIBLE
            try {
                val resName = resources.getResourceEntryName(imgRes)
                Log.d("QuizApp", "Pregunta ${currentIndex + 1}: recurso cargado = $resName (id=$imgRes)")
            } catch (e: Exception) {
                Log.d("QuizApp", "Pregunta ${currentIndex + 1}: recurso cargado id=$imgRes")
            }
        } else {
            imageQuestion.visibility = View.GONE
            Log.d("QuizApp", "Imagen no encontrada: ${q.imageName}")
        }
        textQuestion.text = q.text
        btn1.text = q.answers[0]
        btn2.text = q.answers[1]
        btn3.text = q.answers[2]
        btn4.text = q.answers[3]

        val listener = View.OnClickListener { v ->
            val selected = when (v.id) {
                R.id.btnAnswer1 -> 0
                R.id.btnAnswer2 -> 1
                R.id.btnAnswer3 -> 2
                else -> 3
            }
            answerSelected(selected)
        }
        btn1.setOnClickListener(listener)
        btn2.setOnClickListener(listener)
        btn3.setOnClickListener(listener)
        btn4.setOnClickListener(listener)
    }

    private fun answerSelected(selectedIndex: Int) {
        val q = questions[currentIndex]
        if (selectedIndex == q.correctIndex) correct++ else incorrect++
        currentIndex++
        showQuestion()
    }
}
