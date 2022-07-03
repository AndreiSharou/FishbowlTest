package com.example.data.resource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.resource.local.room.model.AuthSessionLocalModel

@Dao
interface AuthSessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAuthSessionData(model: AuthSessionLocalModel)

    @Query("SELECT * FROM AuthSession")
    suspend fun getAuthSessionData(): AuthSessionLocalModel?
}