package com.example.data.resource.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AuthSession")
data class AuthSessionLocalModel(
    @PrimaryKey
    val token: String
)