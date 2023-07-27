package com.enesaksu.seyahatkitabim.view

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.enesaksu.seyahatkitabim.R
import com.enesaksu.seyahatkitabim.databinding.ActivityMapsBinding
import com.enesaksu.seyahatkitabim.model.Place
import com.enesaksu.seyahatkitabim.roomDB.PlaceDao
import com.enesaksu.seyahatkitabim.roomDB.PlaceDatabase
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var sharedPreferences: SharedPreferences
    private var trackBoolean : Boolean? = null
    private var selectedLatitude : Double? = null
    private var selectedLongitude : Double? = null
    private lateinit var db : PlaceDatabase
    private lateinit var placeDao: PlaceDao
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerLauncher()

        sharedPreferences = this.getSharedPreferences("com.enesaksu.seyahatkitabim", MODE_PRIVATE)
        trackBoolean = false

        selectedLongitude = 0.0
        selectedLongitude = 0.0

        binding.saveBtn.setOnClickListener { save(view) }
        binding.deleteBtn.setOnClickListener { delete(view) }

        db = Room.databaseBuilder(applicationContext,PlaceDatabase::class.java,"Places")
            //.allowMainThreadQueries()
            .build()
        placeDao = db.placeDao()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(this)

        locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                trackBoolean = sharedPreferences.getBoolean("trackBoolean",false)
                if (!trackBoolean!!) {
                    var latLng = LatLng(location.latitude, location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    sharedPreferences.edit().putBoolean("trackBoolean",true).apply()
                }
            }
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            //denied
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ){
                Snackbar.make(
                    binding.root,
                    "Permsisson needed for location",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Give PErmisson"){
                    //Request Permisson
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }.show()
            }else{
                //Request Permisson
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }

        }else{
            //Permisson granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null){
                val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15f))
            }
            mMap.isMyLocationEnabled = true
        }





    }

    override fun onMapLongClick(latLng: LatLng) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(latLng))
        selectedLongitude = latLng.longitude
        selectedLatitude =  latLng.latitude
    }

   private fun registerLauncher(){

       permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
           if (it){
               //Permisson granted
               if (ContextCompat.checkSelfPermission(
                       this,
                       Manifest.permission.ACCESS_FINE_LOCATION
                   ) == PackageManager.PERMISSION_GRANTED
               ){
                   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
                   val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                   if (lastLocation != null){
                       val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15f))
                       mMap.isMyLocationEnabled = true
                   }
               }

           }else{
               //Permisson denied
               Toast.makeText(this@MapsActivity, "Permisson needed!", Toast.LENGTH_SHORT).show()
           }
       }

   }


    private fun save (view : View){

        if (selectedLatitude != null && selectedLongitude != null) {
            val place = Place(binding.editTextText.text.toString(), selectedLatitude!!, selectedLongitude!!)
            compositeDisposable.add(
                placeDao.Insert(place)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse)
            )
        }

    }
    private fun delete(view : View){

    }

    private fun handleResponse(){
        val intent = Intent(this@MapsActivity,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


}