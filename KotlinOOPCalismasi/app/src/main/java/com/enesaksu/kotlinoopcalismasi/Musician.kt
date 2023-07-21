package com.enesaksu.kotlinoopcalismasi

class Musician(name: String?, instrument: String?, age: Int?) {

    //Encapsulation
    var name : String? = null
        get
        set

    var instrument : String? = null
        private set
        get

    var age : Int? = null
        set
        get

    private val bandName : String = "Metalica"


    fun retrunBandName(passaword : String) : String{
        if (passaword == "passaword"){
            return bandName
        }else
        {
            return "Wrong  passaword"
        }

    }




}