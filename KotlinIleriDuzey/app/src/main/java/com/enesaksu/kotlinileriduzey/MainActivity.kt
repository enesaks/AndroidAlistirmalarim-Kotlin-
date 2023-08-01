package com.enesaksu.kotlinileriduzey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


//property delegates

private val myVariable by lazy {//Kullanılmadığında başlatılmıyo
    println("hello this a lazy implementation")
    10
}

class MainActivity : AppCompatActivity() ,LifecycleLogger by LifecycleLoggerImplementation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(myVariable)


        //apply
        val intentAply = Intent().apply {
            putExtra(" "," ")
            action  =" "
        }

        //With
        Person("barley",4).apply{
            name = "Barley"
        }.also { println(it.name) }

        val newBarley = Person("barley",4).apply{
            name = "Barley"
        }
        println(newBarley.name)

        val anotherBArney = with(Person("barley",4)){
            name = "Barley"
        }

        //println(anotherBArney)issim değişmez bu kullanımda, boş döner.


        val withBArney = Person("barney",4)

        with(withBArney){
            name = "BArney"
            age =5
        }
        println(withBArney.name)




        //Delegasyon

        registerLifecycleOWner(this)




    }


}

interface LifecycleLogger{

    fun registerLifecycleOWner(owner : LifecycleOwner)
}

class LifecycleLoggerImplementation : LifecycleLogger , LifecycleEventObserver{

    override fun registerLifecycleOWner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        when(event){
            Lifecycle.Event.ON_RESUME -> println("On Resume Exucuted")
            Lifecycle.Event.ON_PAUSE -> println("On Resume Exucuted")
            else -> Unit
        }
    }

}