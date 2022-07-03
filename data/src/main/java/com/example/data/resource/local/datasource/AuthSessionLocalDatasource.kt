package com.example.data.resource.local.datasource

import com.example.data.resource.local.room.model.AuthSessionLocalModel
import com.example.data.resource.local.room.dao.AuthSessionDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthSessionLocalDatasource @Inject constructor(
    private val authSessionDao: AuthSessionDao
) {

    suspend fun saveAuthSession(model: AuthSessionLocalModel) =
        authSessionDao.saveAuthSessionData(model)

    suspend fun getAuthSession() = authSessionDao.getAuthSessionData()

}