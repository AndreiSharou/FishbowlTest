package com.example.domain.model

import com.example.data.resource.local.room.model.*
import com.example.data.resource.local.room.model.SignLocalModel
import com.example.domain.utils.DateUtils
import java.util.*

data class PostDomainModel(
    val _id: String,
    val reactionCounters: ReactionCountersDomainModel?,
    val sign: SignDomainModel?,
    val likesCount: Int,
    val messageType: Int,
    val messageData: MessageDataDomainModel?,
    val text: String,
    val date: Date,
    val feedName: String,
    val feedIcon: String,
)

fun PostLocalModel.toDomainModel() = PostDomainModel(
    _id,
    reactionCounters.toDomainModel(),
    sign.toDomainMosel(),
    likesCount,
    messageType,
    messageData?.toDomainModel(),
    text,
    DateUtils.getDateFromString(date),
    feedName,
    feedIcon
)

fun MessageDataLocalModel.toDomainModel() = MessageDataDomainModel(
    text, linkMetadata
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
    val title: String?,
    val location: String?,
    val signType: Int,
    val firstLastName: FirstLastNameDomainModel?,
    val userColor: String?,
    val username: String?,
    val profileImage: String?,
    val signAccent: Int?
)

data class FirstLastNameDomainModel(
    val _id: String,
    val firstName: String,
    val lastName: String,
)
data class MessageDataDomainModel(
    val text: String?,
    val linkMetadata: LinkMetadataLocalModel?
)

fun FirstLastNameLocalModel.toDomainModel() = FirstLastNameDomainModel(
    _id, firstName, lastName
)

fun SignLocalModel.toDomainMosel() = SignDomainModel(
    _id, professionalTitle, companyDisplayName, title, location, signType, firstLastName?.toDomainModel(), userColor,username, profileImage, signAccent
)

fun ReactionCountersLocalModel.toDomainModel() = ReactionCountersDomainModel(
    like, helpful, smart, funny, uplifting, _id
)
