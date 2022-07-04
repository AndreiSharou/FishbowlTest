package com.example.data.resource.local.room.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.resource.remote.model.*

@Entity(tableName = "Post")
data class PostLocalModel(
    @PrimaryKey
    val _id: String,
    @Embedded(prefix = "reactionCounters_")
    val reactionCounters: ReactionCountersLocalModel,
    @Embedded(prefix = "sign_")
    val sign: SignLocalModel,
    val likesCount: Int,
    val messageType: Int,
    @Embedded(prefix = "messageData_")
    val messageData: MessageDataLocalModel?,
    val text: String,
    val date: String,
    val feedName: String,
    val feedIcon: String,
)

data class ReactionCountersLocalModel(
    val like: Int,
    val helpful: Int,
    val smart: Int,
    val funny: Int,
    val uplifting: Int,
    val _id: String,
)

data class SignLocalModel(
    val _id: String,
    val professionalTitle: String?,
    val companyDisplayName: String?,
    val title: String?,
    val location: String?,
    val signType: Int,
    @Embedded(prefix = "firstLastName_")
    val firstLastName: FirstLastNameLocalModel?,
    val userColor: String?,
    val username: String?,
    @Embedded(prefix = "schoolMeta_")
    val schoolMeta: SchoolMetaLocalModel?,
    val profileImage: String?,
    val signAccent: Int?
)

data class MessageDataLocalModel(
    val text: String?,
    @Embedded(prefix = "linkMetadata_")
    val linkMetadata: LinkMetadataLocalModel?
)

data class LinkMetadataLocalModel(
    val title:String?,
    val metaType: Int?,
    val description: String?,
    val imageUrl: String?,
    val imageSize: String?,
    val domain: String?,
    val url: String?,
)

data class FirstLastNameLocalModel(
    val _id: String,
    val firstName: String,
    val lastName: String,
)

data class SchoolMetaLocalModel(
    val _id: String?,
)

fun PostRemoteModel.toLocalModel() = PostLocalModel(
    _id = _id,
     reactionCounters = reactionCounters.toLocalModel(),
    sign = sign.toLocalModel(),
    likesCount = likesCount,
    messageType = messageType,
    messageData = messageData?.toLocalModel(),
    text = text,
    date = date,
    feedName = feedName,
    feedIcon = feedIcon,
)

fun ReactionCountersRemoteModel.toLocalModel() = ReactionCountersLocalModel(
    like = like,
    helpful = helpful,
    smart = smart,
    funny = funny,
    uplifting = uplifting,
    _id = _id
)

fun SignRemoteModel.toLocalModel() = SignLocalModel(
    _id = _id,
    professionalTitle = professionalTitle,
    companyDisplayName = companyDisplayName,
    title = title,
    location = location,
    signType = signType,
    firstLastName = firstLastName?.toLocalModel(),
    userColor = userColor,
    username = username,
    schoolMeta = schoolMeta?.toLocalModel(),
    profileImage = profileImage,
    signAccent = signAccent,
)

fun MessageDataRemoteModel.toLocalModel() = MessageDataLocalModel(
    text, linkMetadata?.toLocalModel()
)

fun LinkMetadataRemoteModel.toLocalModel() = LinkMetadataLocalModel(
    title, metaType, description, imageUrl, imageSize, domain, url
)

fun FirstLastNameRemoteModel.toLocalModel() = FirstLastNameLocalModel(
    _id = _id,
    firstName = firstName,
    lastName = lastName,
)

fun SchoolMetaRemoteModel.toLocalModel() = SchoolMetaLocalModel(
    _id = _id,
)

