package com.example.domain.results

sealed class CompletableResult {
    object Success : CompletableResult()
    data class Error(val errorMessage: String?) : CompletableResult()
}