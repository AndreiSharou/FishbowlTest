package com.example.domain.model

import com.example.data.resource.local.room.model.PostLocalModel
import com.example.data.resource.local.room.model.ReactionCountersLocalModel
import com.example.data.resource.local.room.model.SignLocalModel

data class PostDomain(
    val _id: String,
    val reactionCounters: ReactionCountersLocalModel,
    val sign: SignLocalModel,
    val likesCount: Int,
    val messageType: Int,
    val messageData: Any?,
    val text: String,
    val date: String,
    val feedName: String,
    val feedIcon: String,
)

fun PostLocalModel.toDomain() = PostDomain(
    _id,
    reactionCounters,
    sign,
    likesCount,
    messageType,
    messageData,
    text,
    date,
    feedName,
    feedIcon
)
