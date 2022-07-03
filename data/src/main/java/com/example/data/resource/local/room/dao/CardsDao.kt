package com.example.data.resource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.resource.local.room.model.CardLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CardsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCards(list: List<CardLocalModel>)

    @Query("SELECT * FROM Card")
    fun getCards(): Flow<List<CardLocalModel>>

    @Query("DELETE FROM Card")
    suspend fun deleteAll()
}