package com.example.films

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnClickMe= findViewById<Button>(R.id.main_activity_register_button)


        btnClickMe.setOnClickListener {
            val registerButton = Intent(this, RegistrationActivity::class.java)
            startActivity(registerButton)
            Toast.makeText(this, "Ты нажал на кнопку", Toast.LENGTH_SHORT).show()
        }

        val btnClickMe2 = findViewById<TextView>(R.id.main_activity_login_text)
        btnClickMe2.setOnClickListener {
            val loginButton = Intent(this, LoginActivity::class.java)
            startActivity(loginButton)
        }
    }
}
