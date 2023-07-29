package com.enesaksu.seyahatkitabim.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.Placeholder
import androidx.recyclerview.widget.RecyclerView
import com.enesaksu.seyahatkitabim.databinding.RecyclerRowBinding
import com.enesaksu.seyahatkitabim.model.Place
import com.enesaksu.seyahatkitabim.view.MapsActivity

class PlaceAdapter(val placeList: List<Place>) : RecyclerView.Adapter<PlaceAdapter.PlaceHolder>() {

    inner class PlaceHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val binding  = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlaceHolder(binding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }


    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.binding.textView.setText(placeList.get(position).name)


        holder.itemView.setOnClickListener {
            val intent = Intent(it.context,MapsActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("selectedPlace",placeList.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }


}