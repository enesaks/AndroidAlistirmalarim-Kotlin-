package com.enesaksu.seyahatkitabim.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.enesaksu.seyahatkitabim.R
import com.enesaksu.seyahatkitabim.adapter.PlaceAdapter
import com.enesaksu.seyahatkitabim.databinding.ActivityMainBinding
import com.enesaksu.seyahatkitabim.databinding.ActivityMapsBinding
import com.enesaksu.seyahatkitabim.model.Place
import com.enesaksu.seyahatkitabim.roomDB.PlaceDao
import com.enesaksu.seyahatkitabim.roomDB.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: ActivityMainBinding
private var composetDisposable = CompositeDisposable()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db = Room.databaseBuilder(applicationContext,PlaceDatabase::class.java,"Places").build()
        val placeDao = db.placeDao()

        composetDisposable.add(
            placeDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )

    }

    private fun handleResponse(placeList : List<Place>){
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = PlaceAdapter(placeList)
            binding.recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater.inflate(R.menu.place_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.addPlace){
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

}