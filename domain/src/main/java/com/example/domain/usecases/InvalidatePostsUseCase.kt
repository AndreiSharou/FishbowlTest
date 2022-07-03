package com.example.domain.usecases

import com.example.data.repositories.PostsRepository
import com.example.data.utils.DispatchersProvider
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class InvalidatePostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val dispatcher: DispatchersProvider
) {

    suspend operator fun invoke() {
        withContext(dispatcher.io) {
            postsRepository.deleteAll()
        }
    }
}