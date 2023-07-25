package com.example.yourdevice

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

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val signupbtn=findViewById<Button>(R.id.signUp)
        val name=findViewById<TextInputEditText>(R.id.tname)
        val Id=findViewById<TextInputEditText>(R.id.tuserId)
        val Email=findViewById<TextInputEditText>(R.id.temail)
        val phone=findViewById<TextInputEditText>(R.id.tphone)
        val password=findViewById<TextInputEditText>(R.id.tpass)

        signupbtn.setOnClickListener {
            val Name=name.text.toString()
            val userId=Id.text.toString()
            val email=Email.text.toString()
            val Phone=phone.text.toString()
            val Password=password.text.toString()

            val user=User(Name,userId,email,Phone,Password)

            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(userId).setValue(user).addOnSuccessListener {
                name.text?.clear()
                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

        val signin=findViewById<TextView>(R.id.signIn)
        signin.setOnClickListener {
            val opensignIn= Intent(this,SignIn::class.java)
            startActivity(opensignIn)
        }


    }
}