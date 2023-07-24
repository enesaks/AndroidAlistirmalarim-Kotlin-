package com.enesaksu.artbook

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.enesaksu.artbook.databinding.ActivityArtDetailsBinding
import com.enesaksu.artbook.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class ArtDetails : AppCompatActivity() {

    private lateinit var binding: ActivityArtDetailsBinding

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent> // Galeriye gitmek için kulanıcaz.
    private lateinit var permissionLauncher: ActivityResultLauncher<String> //İzin almak için.

    var selectedBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtDetailsBinding.inflate(LayoutInflater.from(this))
        val view : View = binding.root
        setContentView(view)

        binding.imageView.setOnClickListener { clicedImg(view) }
        binding.save.setOnClickListener { save(view) }

        registerLauncher()




    }

    fun save(view : View){

        val artName = binding.edtArtName.text.toString()
        val ArtistName = binding.edtArtistName.text.toString()
        val year = binding.edtYear.text.toString()

        if (selectedBitmap != null){
            val smallBitmap = makeSmallerBitMap(selectedBitmap!!,300)
        }




    }


    private fun makeSmallerBitMap(image : Bitmap, maxSize: Int) :  Bitmap{
        var width  = image.width
        var height = image.height

        var bitmapRatio : Double = width.toDouble() / height.toDouble()
        if (bitmapRatio > 1 ){
            //lanscape
            width = maxSize
            val scaleHeight = width / bitmapRatio
            height = scaleHeight.toInt()
        }else{
            //portrait
            height = maxSize
            val scaleWidth = height* bitmapRatio
            width = scaleWidth.toInt()
        }

        return Bitmap.createScaledBitmap(image,width,height,true)
    }


    fun clicedImg(view : View){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            //Android 33+ -> READ_MEDIA_IMAGES
            if(ContextCompat.checkSelfPermission(this@ArtDetails,Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                //İzin verilmedi
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@ArtDetails,Manifest.permission.READ_MEDIA_IMAGES)){
                    Snackbar.make(view,"Permisson needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permisson"){
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    }.show()
                }else{
                    //Request Permisson
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }


            }else
            {//İzin verildi
                val intentToGalary = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalary)

            }
        }else {
            //Android 32- -> READ_EXTARNAL_STORAGE
            if(ContextCompat.checkSelfPermission(this@ArtDetails,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                //İzin verilmedi
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@ArtDetails,Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Snackbar.make(view,"Permisson needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permisson"){
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }.show()
                }else{
                    //Request Permisson
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }


            }else
            {//İzin verildi
                val intentToGalary = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalary)

            }

        }



    }




    private fun registerLauncher(){

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == RESULT_OK){

                val intentFromResult = result.data
                if (intentFromResult != null){
                    val imageData = intentFromResult.data
                    //binding.imageView.setImageURI(imageData)

                    if (imageData != null) {
                        try {
                            if(Build.VERSION.SDK_INT >= 28) {
                                val source = ImageDecoder.createSource(this@ArtDetails.contentResolver, imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }else{
                                selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageData)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

            }
        }


        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){result ->

            if(result){
                //Permisson Granted
                val intentToGalary = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalary)

            }else {
                //Permisson Denied
                Toast.makeText(this@ArtDetails,"Permisson needed for gallary",Toast.LENGTH_SHORT).show()
            }

        }

    }




}