package com.enesaksu.landmarkbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enesaksu.landmarkbook.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var arrayList : ArrayList<LandMark>
private lateinit var landMarkAdaptor: LandMarkAdaptor


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        val eifel = LandMark("Eifel Tower","Paris", R.drawable.eyfel)
        val pissa = LandMark("Pissa Tower","Italy", R.drawable.pisa)
        val colesseom = LandMark("Colleseom","Rome", R.drawable.colosseom)

        arrayList = ArrayList()
        arrayList.add(eifel)
        arrayList.add(pissa)
        arrayList.add(colesseom)




        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        landMarkAdaptor = LandMarkAdaptor(arrayList)
        binding.recyclerView.adapter = landMarkAdaptor



    }


}