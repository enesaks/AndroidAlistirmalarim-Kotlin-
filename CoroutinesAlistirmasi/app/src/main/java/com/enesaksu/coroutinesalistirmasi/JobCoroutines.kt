package com.enesaksu.coroutinesalistirmasi

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    //Job

    runBlocking {

        val myjob= launch {
            delay(2000)
            println("job")
            val secondJob = launch {
                println("job2")
            }

        }

        myjob.invokeOnCompletion {
            println("my Job end")
        }

        myjob.cancel()

    }

}