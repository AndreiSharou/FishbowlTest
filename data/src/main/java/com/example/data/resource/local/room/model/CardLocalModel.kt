package com.example.data.resource.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.resource.local.room.util.DbTypeConverter
import com.example.data.resource.remote.model.AnswersRemoteModel
import com.example.data.resource.remote.model.CardRemoteModel
import com.example.data.resource.remote.model.ContentRemoteModel
import com.example.data.resource.remote.model.ResultsUiRemoteModel

@Entity(tableName = "Card")
data class CardLocalModel(
    @PrimaryKey
    var _id: String,
    var date: String?,
    var enabled: Boolean?,
    var type: Int?,
    @TypeConverters(DbTypeConverter::class)
    var content: ContentLocalModel?,
    var position: Int?,
    var priority: Int?,
    var label: String?,
    var hideAfterClick: Boolean?,
    @TypeConverters(DbTypeConverter::class)
    var navigation: List<String> = listOf()
)


data class ContentLocalModel(
    var resultsUi: ResultsUiLocalModel?,
    var arrowEnabled: Boolean?,
    var titleColor: String?,
    var backgroundColor: String?,
    var textColor: String?,
    var answers: List<AnswersLocalModel> = listOf(),
    var showResults: Boolean?,
    var showHelpLink: Boolean?,
    var title: String?,
    var text: String?
)

data class ResultsUiLocalModel(
    var resultsTitle: String?,
    var resultsSubtitle: String?,
    var resultsButtonText: String?,
    var resultsButtonDeeplink: String?
)

data class AnswersLocalModel(
    var _id: String?,
    var text: String?,
    var color: String?,
    var variant: Int?
)

fun CardRemoteModel.toLocalModel() = CardLocalModel(
    _id,
    date,
    enabled,
    type,
    content?.toLocalModel(),
    position,
    priority,
    label,
    hideAfterClick,
    navigation
)

fun ContentRemoteModel.toLocalModel() = ContentLocalModel(
    resultsUi?.toLocalModel(),
    arrowEnabled,
    titleColor,
    backgroundColor,
    textColor,
    answers.map { it.toLocalModel() },
    showResults,
    showHelpLink,
    title,
    text
)

fun ResultsUiRemoteModel.toLocalModel() = ResultsUiLocalModel(
    resultsTitle, resultsSubtitle, resultsButtonText, resultsButtonDeeplink
)

fun AnswersRemoteModel.toLocalModel() = AnswersLocalModel(
    _id, text, color, variant
)
