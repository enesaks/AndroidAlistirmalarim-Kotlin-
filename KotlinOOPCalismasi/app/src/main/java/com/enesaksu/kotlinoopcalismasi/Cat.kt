package com.enesaksu.kotlinoopcalismasi

class Cat : Animal() {

    fun test(){
        super.sing()
    }

    override fun sing(){
        println("Sing Cat")
    }

}