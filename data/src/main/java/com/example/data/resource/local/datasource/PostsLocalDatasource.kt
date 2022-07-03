package com.example.data.resource.local.datasource

import com.example.data.resource.local.room.dao.CardsDao
import com.example.data.resource.local.room.dao.PostsDao
import com.example.data.resource.local.room.model.CardLocalModel
import com.example.data.resource.local.room.model.PostLocalModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsLocalDatasource @Inject constructor(
    private val postsDao: PostsDao,
    private val carsDao: CardsDao,
) {

    suspend fun savePosts(list: List<PostLocalModel>) =
        postsDao.savePosts(list)
    suspend fun getPosts() = postsDao.getPosts()
    suspend fun getPostsCount() = postsDao.getPostsCount()


    suspend fun saveCards(list: List<CardLocalModel>) =
        carsDao.saveCards(list)
    suspend fun getCards() = carsDao.getCards()

    suspend fun deleteAll() {
        postsDao.deleteAll()
        carsDao.deleteAll()
    }
}