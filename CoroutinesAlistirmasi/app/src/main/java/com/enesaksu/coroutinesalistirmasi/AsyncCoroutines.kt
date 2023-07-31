package com.enesaksu.coroutinesalistirmasi

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    var username = ""
    var userAge = 0

    runBlocking {

        /*Yanlış Senkorizasyon Kullanımı
        launch {
            val downloadNAme = downloadName()
            println(downloadNAme)
            username = downloadNAme
        }

        launch {
            val downloadAge = downloadAge()
            println(downloadAge)
            userAge = downloadAge
        }

        println(username+" "+userAge)

         */

        val downloadedName = async {
            downloadName()
        }

        val downloadedAge = async {
            downloadAge()
        }
        username = downloadedName.await()
        userAge = downloadedAge.await()

        println(username+" "+userAge)



    }




}

suspend fun downloadName() : String{
    delay(2000)
    val username = "Atil :"
    println("username download")
    return username
}

suspend fun downloadAge(): Int{
    delay(4000)
    val userAge = 50
    println("userAge Download")
    return userAge
}