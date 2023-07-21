package com.enesaksu.kotlinoopcalismasi

class Usser {

    //Property
    var name : String? = null
    var age : Int? = null

    //Constructur vs init
    constructor(name: String, age : Int){
        this.name = name
        this.age = age
        println("Constructer")
    }

    init {
        println("Init")
    }




}