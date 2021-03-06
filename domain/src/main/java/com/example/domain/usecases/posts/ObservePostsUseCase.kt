package com.example.domain.usecases.posts

import com.example.data.repositories.PostsRepository
import com.example.data.utils.DispatchersProvider
import com.example.domain.model.PostDomainModel
import com.example.domain.model.toDomainModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ObservePostsUseCase  @Inject constructor(
    private val postsRepository: PostsRepository,
    private val dispatcher: DispatchersProvider
) {

    suspend operator fun invoke(): Flow<List<PostDomainModel>> {
        return withContext(dispatcher.io) {
            postsRepository.observePosts().map { list ->
                list.map { it.toDomainModel() }
            }
        }
    }
}