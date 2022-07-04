package com.example.fishbowltest.screen.main

import com.example.domain.model.CardDomainModel
import com.example.domain.model.PostDomainModel

sealed class MainFeedModel {

    data class PostModel(val data: PostDomainModel) :
        MainFeedModel()

    data class CardModel(val data: CardDomainModel) :
        MainFeedModel()
}