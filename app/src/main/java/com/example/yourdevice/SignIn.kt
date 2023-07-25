package com.example.yourdevice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.NonCancellable.children

class SignIn : AppCompatActivity() {

    lateinit var databaseref:DatabaseReference


    companion object{
        const val KEY1="com.example.Your Device.SignIn.name"
        const val KEY2="com.example.Your Device.SignIn.email"
        const val KEY3="com.example.Your Device.SignIn.id"
        const val KEY4="com.example.Your Device.SignIn.password"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signinBtn=findViewById<Button>(R.id.signIn)
        val uId=findViewById<TextInputEditText>(R.id.tuserId)
        val pswrd=findViewById<TextInputEditText>(R.id.tpass)

        signinBtn.setOnClickListener {
            val userid=uId.text.toString()
            val password=pswrd.text.toString()
            if(userid.isNotEmpty() && password.isNotEmpty()){
                readDatabase(userid,password)
            }
            else{
                Toast.makeText(this,"Please enter username and Password", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readDatabase(userid: String, password: String) {
        databaseref= FirebaseDatabase.getInstance().getReference("Users")
        databaseref.child(userid).get().addOnSuccessListener{

                if (it.exists()) {
                    if (it.child("pass").value==password) {

                        val username = it.child("name").value
                        val mail = it.child("email").value
                        val usrid = it.child("userId").value
                        val pwrd = it.child("pass").value

                        val intentnav = Intent(this, navdrawer::class.java)
                        intentnav.putExtra(KEY1, username.toString())
                        intentnav.putExtra(KEY2, mail.toString())
                        intentnav.putExtra(KEY3, usrid.toString())
                        intentnav.putExtra(KEY4, pwrd.toString())
                        startActivity(intentnav)
                    }
                    else{
                        Toast.makeText(this, "Please Enter Valid Password.", Toast.LENGTH_SHORT).show()
                    }
                }

                 else {
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                }


            }.addOnFailureListener {
                Toast.makeText(this,"Database failure",Toast.LENGTH_SHORT).show()
            }
        }


    }

