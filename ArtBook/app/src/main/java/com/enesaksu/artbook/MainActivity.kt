package com.enesaksu.artbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesaksu.artbook.databinding.ActivityMainBinding
import java.lang.Exception

private lateinit var binding: ActivityMainBinding

private lateinit var artArray: ArrayList<Art>
private lateinit var artAdapter: ArtAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view : View = binding.root
        setContentView(view)

        artArray = ArrayList<Art>()

        artAdapter = ArtAdapter(artArray)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = artAdapter


        try {
            val database = this.openOrCreateDatabase("arts", MODE_PRIVATE,null)

            val cursor = database.rawQuery("SELECT * FROM arts",null)
            val artNameIx = cursor.getColumnIndex("artname")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                val name = cursor.getString(artNameIx)
                val id = cursor.getInt(idIx)
                val art = Art(name,id)
                artArray.add(art)
            }

            artAdapter.notifyDataSetChanged()

            cursor.close()


        }catch (e : Exception){
            e.printStackTrace()
        }




    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menuadd, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addArt){
            val intent = Intent(this@MainActivity,ArtDetails::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
            return true
        }else
            return super.onContextItemSelected(item)
    }

}