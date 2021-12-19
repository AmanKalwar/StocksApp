package com.example.kaninistocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DeleteAccount : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_delete)
        sessionManager = SessionManager(this)

        val userEmail=findViewById<TextView>(R.id.textView9)

        userEmail.setText(sessionManager.fetchEmail())
    }
}