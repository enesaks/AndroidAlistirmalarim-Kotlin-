package com.enesaksu.androidkotlintemel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.enesaksu.androidkotlintemel.databinding.ActivityMainBinding

lateinit var editText1 : EditText
lateinit var editText2 : EditText
lateinit var textView : TextView
lateinit var button : Button
private lateinit var binding: ActivityMainBinding

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)


            /*
            editText1 = findViewById<EditText>(R.id.editTextText)
            editText2 = findViewById<EditText>(R.id.editTextText2)
            textView = findViewById<TextView>(R.id.textView)
            button = findViewById<Button>(R.id.button2)
             */

            editText1 = binding.editTextText
            editText2 = binding.editTextText2
            textView = binding.textView
            button = binding.buttonTopla


            //button.setOnClickListener {
        //         textView.text = "Result : "+(editText1.text.toString().toInt()+editText2.text.toString().toInt())
        // }  FArk li bir Button kulanımı.


            //Class Kulanımı
            val homer = Simpson("Homer Simpson",50,"nuclear")
          /*  homer.name = "Homer Simpson"
            homer.age = 50
            homer.job = "nuclear"

           */
            println(homer.job)

            /*
           //Nullablity
           var myString : String? // null da olabilir demek (?)
           myString = null
           print(myString)

           var myInt : Int? = null
           // !! -> null olmadığını belirtiyoruz , ? -> null olabilir
           print(myInt!! * 5) // null olduğu için çalışırsa uygulama çöker ama çalışır.

            print(myInt.minus(10) ?: -10) // ?: -> eyer soldaki işlem gerçekleşmezse sağdakini yapar.


            myInt.let { // Null ise asla çalışmaz.
                print(it + 5)
            }
            */

       }

       fun topla(view: View){
           val num1  = editText1.text.toString().toIntOrNull()
           val num2 = editText2.text.toString().toIntOrNull()

           if (num1 != null && num2 != null) {
               textView.text = "Result : " + (num1 + num2)
           }else{
             val toast =Toast.makeText(this,"Sayı Giriniz",Toast.LENGTH_LONG).show()
           }
       }

        fun cikar(view: View){
            val num1  = editText1.text.toString().toIntOrNull()
            val num2 = editText2.text.toString().toIntOrNull()

            if (num1 != null && num2 != null) {
                textView.text = "Result : " + (num1 - num2)
            }else{
                val toast =Toast.makeText(this,"Sayı Giriniz",Toast.LENGTH_LONG).show()
            }
        }

        fun bol(view: View){
            val num1  = editText1.text.toString().toDoubleOrNull()
            val num2 = editText2.text.toString().toDoubleOrNull()

            if (num1 != null && num2 != null) {
                textView.text = "Result : " + (num1 / num2)
            }else{
                val toast =Toast.makeText(this,"Sayı Giriniz",Toast.LENGTH_LONG).show()
            }
        }

        fun carp(view: View){
            val num1  = editText1.text.toString().toIntOrNull()
            val num2 = editText2.text.toString().toIntOrNull()

            if (num1 != null && num2 != null) {
                textView.text = "Result : " + (num1 * num2)
            }else{
                val toast =Toast.makeText(this,"Sayı Giriniz",Toast.LENGTH_LONG).show()
            }
        }


    }