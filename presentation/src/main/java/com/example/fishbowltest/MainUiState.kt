package com.example.fishbowltest

import com.example.domain.model.CardDomainModel
import com.example.domain.model.PostDomainModel
import com.example.fishbowltest.util.FeedUtils
import com.example.fishbowltest.util.ScreenState

data class MainUiState(
    val postList: List<PostDomainModel> = listOf(),
    val cardsList: List<CardDomainModel> = listOf(),
    val screenState: ScreenState = ScreenState.IDLE,
    val error: String? = null
) {
    val list: List<MainFeedModel>
        get() {
            return FeedUtils.combinePostsAndCards(postList, cardsList)
        }
}