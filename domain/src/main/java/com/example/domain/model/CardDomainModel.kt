package com.example.domain.model

import com.example.data.resource.local.room.model.*

data class CardDomainModel(
    var _id: String,
    var date: String?,
    var enabled: Boolean?,
    var type: Int?,
    var content: ContentDomainModel?,
    var position: Int,
    var priority: Int?,
    var label: String?,
    var hideAfterClick: Boolean?,
    var navigation: List<NavigationDomainModel> = listOf()
)


data class ContentDomainModel(
    var resultsUi: ResultsUiDomainModel?,
    var arrowEnabled: Boolean?,
    var titleColor: String?,
    var backgroundColor: String?,
    var textColor: String?,
    var answers: List<AnswersDomainModel>? = listOf(),
    var showResults: Boolean?,
    var showHelpLink: Boolean?,
    var title: String?,
    var text: String?
)

data class ResultsUiDomainModel(
    var resultsTitle: String?,
    var resultsSubtitle: String?,
    var resultsButtonText: String?,
    var resultsButtonDeeplink: String?
)

data class AnswersDomainModel(
    var _id: String?,
    var text: String?,
    var color: String?,
    var variant: Int?
)

data class NavigationDomainModel(
    val page: String?
)

fun CardLocalModel.toDomainModel() = CardDomainModel(
    _id,
    date,
    enabled,
    type,
    content?.toDomainModel(),
    position,
    priority,
    label,
    hideAfterClick,
    navigation.map { it.toDomainModel() }
)

fun ContentLocalModel.toDomainModel() = ContentDomainModel(
    resultsUi?.toDomainModel(),
    arrowEnabled,
    titleColor,
    backgroundColor,
    textColor,
    answers?.map { it.toDomainModel() },
    showResults,
    showHelpLink,
    title,
    text
)

fun ResultsUiLocalModel.toDomainModel() = ResultsUiDomainModel(
    resultsTitle, resultsSubtitle, resultsButtonText, resultsButtonDeeplink
)

fun AnswersLocalModel.toDomainModel() = AnswersDomainModel(
    _id, text, color, variant
)
fun NavigationLocalModel.toDomainModel() = NavigationDomainModel(
    page
)