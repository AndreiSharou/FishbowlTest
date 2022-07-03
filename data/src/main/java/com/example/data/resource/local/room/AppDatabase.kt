package com.example.data.resource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.resource.local.room.dao.AuthSessionDao
import com.example.data.resource.local.room.dao.CardsDao
import com.example.data.resource.local.room.dao.PostsDao
import com.example.data.resource.local.room.model.AuthSessionLocalModel
import com.example.data.resource.local.room.model.CardLocalModel
import com.example.data.resource.local.room.model.PostLocalModel
import com.example.data.resource.local.room.util.DbTypeConverter

@Database(
    entities = [
        AuthSessionLocalModel::class,
        PostLocalModel::class,
        CardLocalModel::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(DbTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authSessionDao(): AuthSessionDao
    abstract fun postsDao(): PostsDao
    abstract fun cardsDao(): CardsDao
}