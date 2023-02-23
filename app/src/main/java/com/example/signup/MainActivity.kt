package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPassword = findViewById<TextView>(R.id.tvPassword)
        val tvUniqueId = findViewById<TextView>(R.id.tvUniqueId)

        val name = intent.getStringExtra(SignIn.KEY1)
        val email = intent.getStringExtra(SignIn.KEY2)
        val password = intent.getStringExtra(SignIn.KEY3)
        val uniqueId= intent.getStringExtra(SignIn.KEY4)

        tvWelcome.text = "Welcome, $name"
        tvName.text = "Name: $name"
        tvEmail.text = "Email: $email"
        tvPassword.text = "Password: $password"
        tvUniqueId.text = "UniqueId: $uniqueId"
    }
}