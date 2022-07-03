package com.example.fishbowltest

import com.example.domain.model.CardDomainModel
import com.example.domain.model.PostDomainModel

sealed class MainFeedModel {

    abstract val id: String

    data class PostModel(val data: PostDomainModel, override val id: String = data._id) :
        MainFeedModel()

    data class CardModel(val data: CardDomainModel, override val id: String = data._id) :
        MainFeedModel()
}