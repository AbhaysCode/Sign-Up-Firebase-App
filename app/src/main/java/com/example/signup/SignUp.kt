package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etUniqueId = findViewById<TextInputEditText>(R.id.etUniqueId)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)
        btnSignUp.setOnClickListener{
            var name = etName.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            var uniqueId = etUniqueId.text.toString()
            val user = User(name,email,password,uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).get().addOnSuccessListener {
                if(it.exists()){
                    Toast.makeText(this,"Id Not Unique Try Again !",Toast.LENGTH_SHORT).show()
                }else{
                    database.child(uniqueId).setValue(user).addOnSuccessListener {
                        etName.text?.clear()
                        etEmail.text?.clear()
                        etPassword.text?.clear()
                        etUniqueId.text?.clear()
                        Toast.makeText(this,"User Successfully Registered", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener{
                        Toast.makeText(this,"Registration Failed !! Server Issue",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        tvSignIn.setOnClickListener{
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }

    }
}