package com.enesaksu.androidkotlintemel_2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.enesaksu.androidkotlintemel_2.databinding.ActivityMainBinding
import java.util.Objects

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Toast, AlertDialog, Snackbar  Kullanımı...

        /*
        Context
        Activty Context -> this
        Application Context -> aplicationContext
         */

        //Toast
        //Toast.makeText(this@MainActivity,"Hoşgeldiniz",Toast.LENGTH_LONG).show()



        //Lambda functions
        binding.button.setOnClickListener {
            save(view)
        }

        binding.goToThrtyActivite.setOnClickListener {
            goToThirtyActvty(view)
        }
        binding.sayac.setOnClickListener {
            goToSayacActvty(view)
        }
/*
        button.setOnClickListener(object : View.OnClickListener {// 2.Kullanımı.
            override fun onClick(p0: View?) {

            }



        })
    */





    }

    fun save (view : View){
        //AlertDialog
        val alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog
            .setTitle("Tittle")
            .setMessage("Message")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {  //1.Kullanımı
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    //Yes e tıklarsa ne yapılcağını belirtiğimiz yer.
                    Toast.makeText(this@MainActivity,"Saved",Toast.LENGTH_SHORT).show()


                }

            })
            .setNegativeButton("No"){p0,p1 ->  //2.Kullanımı
                Toast.makeText(this@MainActivity,"Not Saved",Toast.LENGTH_SHORT).show()
            }
            .show();


    }
    fun goSecondActvty(view : View){
        val intent : Intent = Intent(this@MainActivity,MainActivity2::class.java)
        val name = binding.edtName.text.toString()
        intent.putExtra("name",name)

        startActivity(intent)
    }

    fun goToThirtyActvty(view : View){
        val intent : Intent = Intent(this@MainActivity,MainActivity3::class.java)
        finish()
        startActivity(intent)
    }

    fun goToSayacActvty(view : View){
        val intent : Intent = Intent(this@MainActivity,MainActivitySayaclar::class.java)
        finish()
        startActivity(intent)
    }
}