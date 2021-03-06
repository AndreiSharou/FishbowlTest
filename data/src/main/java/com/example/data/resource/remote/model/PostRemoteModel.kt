package com.example.data.resource.remote.model

data class PostRemoteModel(
    val _id: String,
    val reactionCounters: ReactionCountersRemoteModel,
    val sign: SignRemoteModel,
    val likesCount: Int,
    val messageType: Int,
    val messageData: MessageDataRemoteModel?,
    val text: String,
    val date: String,
    val feedName: String,
    val feedIcon: String,
)

data class ReactionCountersRemoteModel(
    val like: Int,
    val helpful: Int,
    val smart: Int,
    val funny: Int,
    val uplifting: Int,
    val _id: String,
)

data class SignRemoteModel(
    val _id: String,
    val professionalTitle: String?,
    val companyDisplayName: String?,
    val title: String?,
    val location: String?,
    val signType: Int,
    val firstLastName: FirstLastNameRemoteModel?,
    val userColor: String?,
    val username: String?,
    val schoolMeta: SchoolMetaRemoteModel?,
    val profileImage: String?,
    val signAccent: Int?
)

data class MessageDataRemoteModel(
    val text: String?,
    val linkMetadata: LinkMetadataRemoteModel?
)

data class LinkMetadataRemoteModel(
    val title:String?,
    val metaType: Int?,
    val description: String?,
    val imageUrl: String?,
    val imageSize: String?,
    val domain: String?,
    val url: String?,
)

data class FirstLastNameRemoteModel(
    val _id: String,
    val firstName: String,
    val lastName: String,
)

data class SchoolMetaRemoteModel(
    val _id: String?,
)