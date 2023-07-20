package com.enesaksu.storingdata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.enesaksu.storingdata.databinding.ActivityMainBinding
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view  = binding.root
        setContentView(view)

        sharedPref = getSharedPreferences("com.enesaksu.storingdata",Context.MODE_PRIVATE)
        var ageSaved = sharedPref.getInt("age",-1)
        if(ageSaved != -1){
            binding.textView.setText("AGE : $ageSaved")
        }else
        {
            binding.textView.setText("ENTER AGE")
        }



    }


    fun save(view : View){
        var age = binding.editText.text.toString().toIntOrNull()
        if (age != null) {
            binding.textView.setText("AGE : $age")
            sharedPref.edit().putInt("age", age).apply()
        }else{
            Toast.makeText(this,"Please Enter Age!!",Toast.LENGTH_LONG).show()
        }


    }
    fun delete(view : View){
        sharedPref.edit().clear().apply();
        binding.textView.setText("ENTER AGE")

    }

}