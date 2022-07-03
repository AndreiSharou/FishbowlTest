package com.example.domain.usecases.metacards

import com.example.data.repositories.PostsRepository
import com.example.data.utils.DispatchersProvider
import com.example.domain.model.CardDomainModel
import com.example.domain.model.toDomainModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ObserveMetaCardsUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val dispatcher: DispatchersProvider
) {

    suspend operator fun invoke(): Flow<List<CardDomainModel>> {
        return withContext(dispatcher.io) {
            postsRepository.observeMetaCards().map { list ->
                list.map { it.toDomainModel() }
            }
        }
    }
}