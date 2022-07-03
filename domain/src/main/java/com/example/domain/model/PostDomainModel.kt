package com.example.domain.model

import com.example.data.resource.local.room.model.*
import com.example.data.resource.local.room.model.SignLocalModel

data class PostDomainModel(
    val _id: String,
    val reactionCounters: ReactionCountersDomainModel?,
    val sign: SignDomainModel?,
    val likesCount: Int,
    val messageType: Int,
    val messageData: Any?,
    val text: String,
    val date: String,
    val feedName: String,
    val feedIcon: String,
)

fun PostLocalModel.toDomain() = PostDomainModel(
    _id,
    reactionCounters.toDomainModel(),
    sign.toDomainMosel(),
    likesCount,
    messageType,
    messageData,
    text,
    date,
    feedName,
    feedIcon
)

data class ReactionCountersDomainModel(
    val like: Int,
    val helpful: Int,
    val smart: Int,
    val funny: Int,
    val uplifting: Int,
    val _id: String,
)

data class SignDomainModel(
    val _id: String,
    val professionalTitle: String?,
    val companyDisplayName: String?,
    val signType: Int,
    val firstLastName: FirstLastNameDomainModel?,
    val userColor: String?,
    val profileImage: String?,
    val signAccent: Int?
)

data class FirstLastNameDomainModel(
    val _id: String,
    val firstName: String,
    val lastName: String,
)

fun FirstLastNameLocalModel.toDomainModel() = FirstLastNameDomainModel(
    _id, firstName, lastName
)

fun SignLocalModel.toDomainMosel() = SignDomainModel(
    _id, professionalTitle, companyDisplayName, signType, firstLastName?.toDomainModel(), userColor, profileImage, signAccent
)

fun ReactionCountersLocalModel.toDomainModel() = ReactionCountersDomainModel(
    like, helpful, smart, funny, uplifting, _id
)
