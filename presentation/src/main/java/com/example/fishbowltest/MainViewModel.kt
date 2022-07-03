package com.example.fishbowltest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.InvalidatePostsUseCase
import com.example.domain.usecases.LoadPostsUseCase
import com.example.domain.usecases.ObservePostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val invalidatePostsUseCase: InvalidatePostsUseCase,
    private val observePostsUseCase: ObservePostsUseCase,
    private val loadPostsUseCase: LoadPostsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState(isLoading = false))
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        viewModelScope.launch {
            invalidatePostsUseCase.invoke()
            launch {
                observePostsUseCase.invoke().collect {
                    _uiState.value = _uiState.value.copy(postList = it)
                }
            }
            loadNewPage()
        }
    }

    fun loadNewPage() {
        if (!_uiState.value.isLoading) {
            viewModelScope.launch {
                loadPosts()
            }
        }
    }

    private suspend fun loadPosts() {

        _uiState.value = _uiState.value.copy(isLoading = true)
        loadPostsUseCase.invoke()
        _uiState.value = _uiState.value.copy(isLoading = false)

    }
}