package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    companion object{
        val KEY1 = "com.example.signup.SignIn.KEY.name"
        val KEY2 = "com.example.signup.SignIn.KEY.email"
        val KEY3 = "com.example.signup.SignIn.KEY.password"
        val KEY4 = "com.example.signup.SignIn.KEY.uniqueId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val etUniqueIdSignIn = findViewById<TextInputEditText>(R.id.etUniqueIdSignIn)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        btnSignIn.setOnClickListener{
            val uniqueId = etUniqueIdSignIn.text.toString()
            readData(uniqueId)
        }
    }

    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if(it.exists()){
                val intent = Intent(this,MainActivity::class.java)
                val name = it.child("name").value.toString()
                val email = it.child("email").value.toString()
                val password = it.child("password").value.toString()
                val uniqueId = it.child("uniqueId").value.toString()
                intent.putExtra(KEY1,name)
                intent.putExtra(KEY2,email)
                intent.putExtra(KEY3,password)
                intent.putExtra(KEY4,uniqueId)
                startActivity(intent)
            }else{
                Toast.makeText(this,"User Not Found, First Sign Up", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Registration Failed !! Server Issue",Toast.LENGTH_LONG).show()
        }
    }
}