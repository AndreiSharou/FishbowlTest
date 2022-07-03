package com.example.data.resource.remote.model

data class CardRemoteModel(
    var _id: String,
    var date: String?,
    var enabled: Boolean?,
    var type: Int?,
    var content: ContentRemoteModel?,
    var position: Int,
    var priority: Int?,
    var label: String?,
    var hideAfterClick: Boolean?,
    var navigation: List<NavigationRemoteModel> = listOf()
)

data class ContentRemoteModel(
    var resultsUi: ResultsUiRemoteModel?,
    var arrowEnabled: Boolean?,
    var titleColor: String?,
    var backgroundColor: String?,
    var textColor: String?,
    var answers: List<AnswersRemoteModel>? = listOf(),
    var showResults: Boolean?,
    var showHelpLink: Boolean?,
    var title: String?,
    var text: String?
)

data class ResultsUiRemoteModel(
    var resultsTitle: String?,
    var resultsSubtitle: String?,
    var resultsButtonText: String?,
    var resultsButtonDeeplink: String?
)

data class AnswersRemoteModel(
    var _id: String?,
    var text: String?,
    var color: String?,
    var variant: Int?
)

data class NavigationRemoteModel(
    val page: String?
)
