package com.enesaksu.seyahatkitabim.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.enesaksu.seyahatkitabim.model.Place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.internal.operators.completable.CompletableAmb

@Dao
interface PlaceDao {

    @Query("SELECT * FROM Place")
    fun getAll() : Flowable<List<Place>>

    @Insert
    fun Insert(place : Place) : Completable

    @Delete
    fun delete(place: Place) : Completable

}