package com.example.data.resource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.resource.local.room.model.PostLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePosts(list: List<PostLocalModel>)

    @Query("SELECT * FROM Post")
    fun getPosts(): Flow<List<PostLocalModel>>

    @Query("SELECT COUNT(_id) FROM Post")
    fun getPostsCount(): Int

    @Query("DELETE FROM Post")
    suspend fun deleteAll()
}