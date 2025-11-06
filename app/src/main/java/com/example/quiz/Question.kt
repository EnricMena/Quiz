package com.example.quiz

data class Question(
    val text: String,
    val answers: List<String>,
    val correctIndex: Int,
    val imageName: String
)
