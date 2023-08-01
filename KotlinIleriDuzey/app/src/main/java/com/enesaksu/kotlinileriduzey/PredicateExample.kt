package com.enesaksu.kotlinileriduzey

fun main(){

    val myNumKist = listOf<Int>(1,3,5,8,9,76,4,3,67,12)


    val allChek = myNumKist.all { it > 5 }
    println(allChek)

    val anyCheck = myNumKist.any { it > 5 }
    println(anyCheck)

    val totalCount = myNumKist.count{it > 5}
    println(totalCount)

    val findNum = myNumKist.find{it > 5}
    println(findNum)

    val findNumLast = myNumKist.findLast{it > 5}
    println(findNumLast)

    val myPredicate = {num : Int -> num > 5}
    val allCheckPradicate = myNumKist.all(myPredicate)
    println(allCheckPradicate)

}