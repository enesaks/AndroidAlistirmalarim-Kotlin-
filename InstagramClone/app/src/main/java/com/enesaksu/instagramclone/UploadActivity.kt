package com.enesaksu.instagramclone

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.enesaksu.instagramclone.databinding.ActivityMainBinding
import com.enesaksu.instagramclone.databinding.ActivityUploadBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import java.net.URL
import java.util.Objects
import java.util.UUID

private lateinit var binding: ActivityUploadBinding

private lateinit var firebaseFirestore: FirebaseFirestore
private lateinit var auth : FirebaseAuth
private lateinit var firebaseStorage: FirebaseStorage
private lateinit var storageReference :StorageReference

private var imageData : Uri? = null

private lateinit var permissionLauncher: ActivityResultLauncher<String> // İzin İçin
private lateinit var activityResultLauncher: ActivityResultLauncher<Intent> // Galeriye Gitmek için


class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.uploadBtn.setOnClickListener { upload(view) }
        binding.imageView.setOnClickListener { uploadImg(view) }

        registerLauncher()

        auth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference




    }

    fun upload(view : View){//Database e verileri kaydetme buton işlemleri...
        binding.uploadBtn.isEnabled = false

        if (imageData != null){

            var uuid = UUID.randomUUID()
            var imageName = "images/${uuid}.jpeg"

            storageReference.child(imageName).putFile(imageData!!)
                .addOnSuccessListener {
                    Toast.makeText(this@UploadActivity,"Post Uploaded",Toast.LENGTH_SHORT).show()
                    var newReference = firebaseStorage.getReference(imageName)

                    newReference.downloadUrl.addOnSuccessListener { uri->
                        val dowloandUri = uri.toString()
                        val comment = binding.edtComment.text.toString()

                        val user = auth.currentUser
                        val email = user?.email.toString()
                        val title = binding.edtTitle.text.toString()

                        val postData : HashMap<String,Any> = HashMap()
                        postData.put("useremail",email)
                        postData.put("downloadurl",dowloandUri)
                        postData.put("Title",title)
                        postData.put("comment",comment )
                        postData.put("date",FieldValue.serverTimestamp())

                        firebaseFirestore.collection("Posts").add(postData).addOnSuccessListener {

                            val intent = Intent(this,FeedActivty::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)

                        }.addOnFailureListener { e->//firebasFirestore
                            Toast.makeText(this@UploadActivity,e.localizedMessage,Toast.LENGTH_SHORT).show()
                        }

                    }.addOnFailureListener { e->//newReference
                        Toast.makeText(this@UploadActivity,e.localizedMessage,Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener{e->//storageReference
                    Toast.makeText(this@UploadActivity,e.localizedMessage,Toast.LENGTH_SHORT).show()
                }

        }




    }

    fun uploadImg(view : View){//Fotoğraf seçme...

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){ //Android 33+ -> READ_MEDIA_IMAGES

            if(ActivityCompat.checkSelfPermission(this@UploadActivity,Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED){
                //İzin Verilmedi
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@UploadActivity,Manifest.permission.READ_MEDIA_IMAGES)){
                    Snackbar.make(view,"Permisson needed for Gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permision"){
                        //İzin Alma kısmı
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    }.show()
                }else{
                    //İzin Alma kısmı
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            }else{
                //İzin Verildi
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }

        }//Android 33+ -> READ_MEDIA_IMAGES
        else{//Android 33- -> READ_EXTERNAL_STORAGE

            if(ActivityCompat.checkSelfPermission(this@UploadActivity,Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
                //İzin Verilmedi
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@UploadActivity,Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Snackbar.make(view,"Permisson needed for Gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permision"){
                        //İzin Alma kısmı
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }.show()
                }else{
                    //İzin Alma kısmı
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }else{
                //İzin Verildi
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }

        }//Android 33- -> READ_EXTERNAL_STORAGE





    }

    fun registerLauncher(){//Galeriye gitme ve izin işlemleri.

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->

            if (result.resultCode == RESULT_OK){
                val intentfromResult = result.data

                if (intentfromResult != null){
                    imageData = intentfromResult.data
                    binding.imageView.setImageURI(imageData)
                }


            }

        }//activtyResultLauncher

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){result->

            if (result){
                //Permisson granted
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)

            }else{
                //PErmisson Denied
                Toast.makeText(this@UploadActivity,"PErmisson needed for gallery",Toast.LENGTH_SHORT)
            }

        }//PermissionLauncher



    }//RegisterLauncher




}