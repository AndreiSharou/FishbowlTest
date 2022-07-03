package com.example.fishbowltest

import com.example.domain.model.PostDomain
import com.example.fishbowltest.util.ScreenState

data class MainUiState(
    val postList: List<PostDomain> = listOf(),
    val screenState: ScreenState = ScreenState.IDLE,
    val error: String? = null
)