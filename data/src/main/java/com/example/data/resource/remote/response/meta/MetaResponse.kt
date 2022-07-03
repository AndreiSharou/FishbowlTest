package com.example.data.resource.remote.response.meta

import com.example.data.resource.remote.model.CardRemoteModel

data class MetaResponse(
    val cards: List<CardRemoteModel> = listOf()
)