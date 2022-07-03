package com.example.data.repositories

import com.example.data.resource.local.datasource.PostsLocalDatasource
import com.example.data.resource.local.room.model.PostLocalModel
import com.example.data.resource.local.room.model.toLocalModel
import com.example.data.resource.remote.ApiService
import com.example.data.resource.remote.response.ResponseErrorMessage
import com.example.data.resource.remote.response.meta.MetaResponse
import com.example.data.resource.remote.response.posts.PostsResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject constructor(
    private val api: ApiService,
    private val localData: PostsLocalDatasource
) {

    suspend fun deleteAll() {
        localData.deleteAll()
    }

    suspend fun loadPosts(): NetworkResponse<PostsResponse, ResponseErrorMessage> {
        val response = api.getPosts(POSTS_COUNT, localData.getPostsCount())
        if (response is NetworkResponse.Success) {
            localData.savePosts(response.body.posts.map { it.toLocalModel() })
        }
        return response
    }

    suspend fun loadMeta(): NetworkResponse<MetaResponse, ResponseErrorMessage> {
        val response = api.getMeta()
        if (response is NetworkResponse.Success) {
            localData.saveCards(response.body.cards.map { it.toLocalModel() })
        }
        return response
    }

    suspend fun observePosts(): Flow<List<PostLocalModel>> {
        return localData.getPosts()
    }


    companion object {
        const val POSTS_COUNT = 10
    }

}