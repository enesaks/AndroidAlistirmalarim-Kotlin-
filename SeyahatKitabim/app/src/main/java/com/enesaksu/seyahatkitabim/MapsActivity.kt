package com.enesaksu.seyahatkitabim

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.enesaksu.seyahatkitabim.databinding.ActivityMapsBinding
import com.google.android.material.snackbar.Snackbar

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permisLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerLauncher()

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {
                var latLng = LatLng(location.latitude,location.longitude)


                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))

            }
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //denied
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(binding.root,"Permsisson needed for location",Snackbar.LENGTH_INDEFINITE).setAction("Give PErmisson"){
                    //Request Permisson
                    permisLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }.show()
            }else{
                //Request Permisson
                permisLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }

        }else{
            //Permisson granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
        }





    }


   private fun registerLauncher(){

       permisLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
           if (it){
               //Permisson granted
               if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)

               }

           }else{
               //Permisson denied
               Toast.makeText(this@MapsActivity,"Permisson needed!",Toast.LENGTH_SHORT).show()
           }
       }

   }



}