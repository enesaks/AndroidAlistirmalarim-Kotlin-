package com.enesaksu.landmarkbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enesaksu.landmarkbook.databinding.ActivityMain2Binding
import com.enesaksu.landmarkbook.databinding.ActivityMainBinding
import com.enesaksu.landmarkbook.databinding.ResyclerlayoutBinding
import com.google.android.filament.View

class LandMarkAdaptor(private val data: ArrayList<LandMark>)
    : RecyclerView.Adapter<LandMarkAdaptor.ViewHolder>() {


    inner class ViewHolder(val binding: ResyclerlayoutBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ResyclerlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.recyTxtName.text = data[position].name
        holder.binding.recyTxtNameCount.text = data[position].country
        holder.binding.recyImageView.setImageResource(data[position].image)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,MainActivity2::class.java)
            //intent.putExtra("landmark",data.get(position))

            Singleton.chosenLandMark = data.get(position)
            holder.itemView.context.startActivity(intent)
        }

    }


}