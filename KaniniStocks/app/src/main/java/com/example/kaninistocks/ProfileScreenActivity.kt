package com.example.kaninistocks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class ProfileScreenActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        sessionManager = SessionManager(this)

        val userEmail =findViewById<TextView>(R.id.textView5)
        val memberSince =findViewById<TextView>(R.id.msgDisplay)


        val deleteAccount = findViewById<Button>(R.id.deleteAccountButton)
        val changeEmail = findViewById<Button>(R.id.changeEmailButton)
        val loginHistory = findViewById<Button>(R.id.ReviewButton)

        userEmail.setText(sessionManager.fetchEmail())
        memberSince.setText(convertLongToTime(sessionManager.fetchMemberSince()))


        deleteAccount.setOnClickListener{
            val intent = Intent(this, DeleteAccount::class.java)
            startActivity(intent)

        }

        changeEmail.setOnClickListener{
            val intent = Intent(this, update_email::class.java)
            startActivity(intent)

        }

        loginHistory.setOnClickListener{
            val intent = Intent(this, LoginHistory::class.java)
            startActivity(intent)

        }
    }

    private fun convertLongToTime(time: Long?): String {
        val date = Date(time!!)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)

    }
}