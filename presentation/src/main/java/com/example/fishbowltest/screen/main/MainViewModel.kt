package com.example.fishbowltest.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.results.CompletableResult
import com.example.domain.usecases.InvalidatePostsRepositoryDataUseCase
import com.example.domain.usecases.metacards.LoadMetaUseCase
import com.example.domain.usecases.metacards.ObserveMetaCardsUseCase
import com.example.domain.usecases.posts.LoadPostsUseCase
import com.example.domain.usecases.posts.ObservePostsUseCase
import com.example.fishbowltest.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val invalidatePostsRepositoryDataUseCase: InvalidatePostsRepositoryDataUseCase,
    private val observePostsUseCase: ObservePostsUseCase,
    private val observeMetaCardsUseCase: ObserveMetaCardsUseCase,
    private val loadPostsUseCase: LoadPostsUseCase,
    private val loadMetaUseCase: LoadMetaUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        viewModelScope.launch {
            invalidatePostsRepositoryDataUseCase.invoke()
            launch {
                observeMetaCardsUseCase.invoke().collect {
                    _uiState.value = _uiState.value.copy(cardsList = it)
                }
            }
            launch {
                observePostsUseCase.invoke().collect {
                    _uiState.value = _uiState.value.copy(postList = it)
                }
            }
            loadMeta()
            loadPosts()
        }
    }

    fun loadNewPage() {
        if (_uiState.value.screenState != ScreenState.LOADING) {
            viewModelScope.launch {
                loadPosts()
            }
        }
    }

    private suspend fun loadPosts() {
        _uiState.value = _uiState.value.copy(screenState = ScreenState.LOADING)
        when (val result = loadPostsUseCase.invoke()) {
            is CompletableResult.Success -> _uiState.value = _uiState.value.copy(screenState = ScreenState.IDLE)
            is CompletableResult.Error -> {
                _uiState.value = _uiState.value.copy(screenState = ScreenState.ERROR, error = result.errorMessage)
            }
        }
    }
    private suspend fun loadMeta() {
        _uiState.value = _uiState.value.copy(screenState = ScreenState.LOADING)
        when (val result = loadMetaUseCase.invoke()) {
            is CompletableResult.Success -> _uiState.value = _uiState.value.copy(screenState = ScreenState.IDLE)
            is CompletableResult.Error -> {
                _uiState.value = _uiState.value.copy(screenState = ScreenState.ERROR, error = result.errorMessage)
            }
        }
    }
}