package com.enesaksu.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesaksu.instagramclone.adapter.PostAdapter
import com.enesaksu.instagramclone.databinding.ActivityFeedActivtyBinding
import com.enesaksu.instagramclone.databinding.ActivityMainBinding
import com.enesaksu.instagramclone.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityFeedActivtyBinding
private lateinit var auth : FirebaseAuth
private lateinit var db : FirebaseFirestore
private lateinit var postArrayList: ArrayList<Post>
private lateinit var postAdapter: PostAdapter

class FeedActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedActivtyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        postArrayList = ArrayList<Post>()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(postArrayList)
        binding.recyclerView.adapter = postAdapter


    }

    private fun getData(){

        db.collection("Posts").addSnapshotListener { value, error ->

            if (error != null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){

                        val documents = value.documents
                        postArrayList.clear()

                        for (document in documents){
                            val comment = document.get("comment") as String
                            val title= document.get("Title") as String
                            val useremail = document.get("useremail") as String
                            val downloadUrl = document.get("downloadurl") as String


                            println(title)

                            val post = Post(useremail, title, comment, downloadUrl)
                            postArrayList.add(post)
                        }

                        postAdapter.notifyDataSetChanged()

                    }
                }
            }

        }

    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infilater : MenuInflater = menuInflater
        infilater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.addPost ->{
                val intent = Intent(this@FeedActivty,UploadActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.exit ->{
                auth.signOut()
                val intent = Intent(this@FeedActivty,MainActivity::class.java)
                finish()
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }


}