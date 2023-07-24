package com.enesaksu.artbook

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    private val TABLE_NAME="Art"
    private val COL_ID = "id"
    private val COL_ART = "artname"
    private val COL_ARTIST = "artistname"
    private val COL_YEAR = "year"
    private val COl_IMG : Int = 0
    companion object {
        private val DATABASE_NAME = "ARTBOOK"//database adı
        private val DATABASE_VERSION = 1
    }


    override fun onCreate(p0: SQLiteDatabase?) {




    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}