package com.example.fishbowltest

import com.example.domain.model.PostDomain

data class MainUiState(
    val postList:List<PostDomain> = listOf(),
    val isLoading: Boolean = false
) {
}