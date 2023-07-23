package com.enesaksu.landmarkbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.enesaksu.landmarkbook.databinding.ActivityMain2Binding
import com.enesaksu.landmarkbook.databinding.ActivityMainBinding

private lateinit var binding: ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)


        val intent = intent
        //val selectedLandMark = intent.getSerializableExtra("landmark")  as LandMark
        val selectedLandMark = Singleton.chosenLandMark

        selectedLandMark?.let {
            binding.imageView.setImageResource(selectedLandMark!!.image)
            binding.txtName.text = selectedLandMark.name
            binding.txtCountry.text = selectedLandMark.country
        }









    }
}