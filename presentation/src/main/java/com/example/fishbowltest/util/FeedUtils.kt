package com.example.fishbowltest.util

import com.example.domain.model.CardDomainModel
import com.example.domain.model.PostDomainModel
import com.example.fishbowltest.MainFeedModel

object FeedUtils {

    fun combinePostsAndCards(
        postList: List<PostDomainModel>, cardsList: List<CardDomainModel>
    ): List<MainFeedModel> {
        val feedList: MutableList<MainFeedModel> = mutableListOf()

        val cardsListFilteredByType =
            cardsList.filter { (it.type == 0 || it.type == 2) && it.position <= postList.size }
                .toMutableList()
        val cardsListFilteredByPriority = filterOutWithSamePosition(cardsListFilteredByType)

        feedList.addAll(postList.map { MainFeedModel.PostModel(it) })
        cardsListFilteredByPriority.forEach {
            feedList.add(it.position, MainFeedModel.CardModel(it))
        }


        return feedList
    }

    private fun filterOutWithSamePosition(cardsList: MutableList<CardDomainModel>): List<CardDomainModel> {
        val filteredList = cardsList

        cardsList.forEachIndexed { index, cardDomainModel ->
            cardsList.find { it.position == cardDomainModel.position }?.let {
                if (it.priority !== null && cardDomainModel.priority != null) {
                    if (it.priority!! < cardDomainModel.priority!!) {
                        filteredList.removeAt(index)
                    }
                }
            }
        }

        return filteredList
    }
}