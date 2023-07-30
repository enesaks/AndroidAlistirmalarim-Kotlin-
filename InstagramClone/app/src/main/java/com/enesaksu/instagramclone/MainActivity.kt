package com.enesaksu.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.enesaksu.instagramclone.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext.Auth

private lateinit var binding: ActivityMainBinding
private lateinit var auth : FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this,FeedActivty::class.java)
            startActivity(intent)
            finish()
        }

        binding.signInBtn.setOnClickListener { signIn(view) }
        binding.signUpBtn.setOnClickListener { signUp(view) }



    }

    fun signUp(view : View){

        val email = binding.edtEmail.text.toString()
        val passaword = binding.edtPassaword.text.toString()

        auth.createUserWithEmailAndPassword(email,passaword).addOnCompleteListener(this){task ->

            if (task.isSuccessful){
                val user = auth.currentUser
                Toast.makeText(this@MainActivity,task.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this@MainActivity, task.exception?.localizedMessage , Toast.LENGTH_SHORT).show()
            }

        }


    }



    fun signIn(view: View){

        val email = binding.edtEmail.text.toString()
        val passaword = binding.edtPassaword.text.toString()

        auth.signInWithEmailAndPassword(email,passaword).addOnCompleteListener(this){task ->

            if (task.isSuccessful){
                val intent = Intent(this,FeedActivty::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this@MainActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }

        }

    }

}