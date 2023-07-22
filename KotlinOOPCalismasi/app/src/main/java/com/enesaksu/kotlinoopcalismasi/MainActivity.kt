package com.enesaksu.kotlinoopcalismasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = Usser("Enes", 21)
        /*
        user.name = "ENes"
        user.age = 21
         */

        println("Name : ${user.name} Age : $user.age")

        val musician = Musician("Enes", "Guitar", 21)


        println(musician.retrunBandName("passawprd"))
        println(musician.retrunBandName("dasd"))

        //Inheritance

        var superMusican = SuperMusican("mehmet", "piano", 23)
        println(superMusican.name)
        println(superMusican.retrunBandName("passaword"))

        //Polymorphism

        //Static Polymorphism
        val mathamatic = Mathametic()

        println(mathamatic.sum())
        println(mathamatic.sum(2, 2))
        println(mathamatic.sum(2, 3, 4))

        //Dynamic Polymorphism

        val cat = Cat()
        println(cat.test())
        println(cat.sing())

        //Abstract
        user.information()
        //var people = People() abstract sınıflardan obje oluşturulmaz.
        val piano = Piano()
        piano.brand = "Yamaha"
        piano.digital = true
        println(piano.roomName)
        piano.info()

        //Lambda Expressions

        fun printString(myString : String){
            println(myString)
        }
        printString("bla bla ... ")

        val lamdaString = {myString : String -> println(myString)}
        lamdaString("bla bla ....")

        val multplayLamda = {a: Int , b : Int -> a*b}
        println(multplayLamda(2,3))

        val multiplayLamda2 : (Int, Int) -> (Int)  = {a,b -> a*b}
        println(multiplayLamda2(2,3))

        //asynchronous -> kulanıcı arayüzünü kitlemeden yapılan işlemler.

        val musicantest = Musician("enes","pianp",21)

        fun dowloandMusician(url : String, completion : (Musician) -> Unit){
            //url -> Dowloand
            //data
            completion(musicantest) // işlemin tamamlandığını söylüyor.
        }


        dowloandMusician("enesaks.com") {
            println("Complete Ready,  Musican ${it.name}")
        }


    }
}