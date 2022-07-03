package com.example.domain.usecases.metacards

import com.example.data.repositories.PostsRepository
import com.example.data.utils.DispatchersProvider
import com.example.data.utils.ResourceProvider
import com.example.domain.R
import com.example.domain.results.CompletableResult
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class LoadMetaUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val resourceProvider: ResourceProvider,
    private val dispatcher: DispatchersProvider
) {

    suspend operator fun invoke() : CompletableResult {
        return withContext(dispatcher.io) {
            when (val result = postsRepository.loadMeta()) {
                is NetworkResponse.Success -> CompletableResult.Success
                is NetworkResponse.ServerError -> CompletableResult.Error(
                    result.body?.error ?: resourceProvider.getString(
                        R.string.server_error
                    )
                )
                is NetworkResponse.NetworkError -> CompletableResult.Error(
                    resourceProvider.getString(
                        R.string.network_error
                    )
                )
                is NetworkResponse.UnknownError -> CompletableResult.Error(
                    resourceProvider.getString(
                        R.string.unknown_error
                    )
                )
            }
        }
    }
}