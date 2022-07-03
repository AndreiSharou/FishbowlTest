package com.example.data.resource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.resource.local.room.dao.AuthSessionDao
import com.example.data.resource.local.room.dao.PostsDao
import com.example.data.resource.local.room.model.AuthSessionLocalModel
import com.example.data.resource.local.room.model.PostLocalModel

@Database(
    entities = [
        AuthSessionLocalModel::class,
        PostLocalModel::class
    ],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authSessionDao(): AuthSessionDao
    abstract fun postsDao(): PostsDao
}