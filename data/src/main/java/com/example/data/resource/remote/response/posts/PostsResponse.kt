package com.example.data.resource.remote.response.posts

import com.example.data.resource.remote.model.PostRemoteModel

data class PostsResponse(
    val posts: List<PostRemoteModel> = listOf()
)
