package com.enesaksu.kotlinileriduzey

fun main(){

    val myNumList = listOf<Int>(1,3,5,6,3,4,8,9,7,9,12,14,35)

    /*
    //val smallNumberList = myNumList.filter { num -> num < 6 }

    val smallNumberList = myNumList.filter { 4 < it }

    for (num in smallNumberList){
        println(num)
    }



    val squaredNumbers = myNumList.map { it * it }
    for (num in squaredNumbers){
        println(num)
    }



    val filterAndMao = myNumList.filter { it < 6 }.map { it * it }
    for (num in filterAndMao){
        println(num)
    }



    val musician = listOf<Musician>(
        Musician("james",60,"guitar"),
        Musician("Lars",55,"Drum"),
        Musician("Kirk",50,"Guitar")
    )

    val musicanFilter = musician
        .filter { it.age < 60 }
        .map { it.insturumant }

    musicanFilter.forEach { music ->
        println(music)
    }
    */

    //Let
    var myInt : Int? = null

    if (myInt != null){
        val num = myInt +1
    }


    myInt?.let {
        val num = it +1
    }
    val myNum = myInt?.let {
        it+1
    }?:0

    //Also
    val ahmet = Person("ahmet",28)
    val mehmet = Person("Mehmet",15)
    val ayse = Person("Ayşe",25)

    val people = listOf<Person>(ahmet,mehmet,ayse)
    people.filter { it.age > 18 }.also {
        for (person in it){
            println(person.name)
        }
    }






}

data class Person(var name :String, var age : Int)

data class Musician(val name : String, val age : Int, val insturumant : String)