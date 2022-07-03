package com.example.data.resource.remote

import com.example.data.resource.local.datasource.AuthSessionLocalDatasource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authSessionLocalDatasource: AuthSessionLocalDatasource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var token: String? = runBlocking {
            authSessionLocalDatasource
                .getAuthSession()
                ?.token
        }

        // cause session-key is hardcoded for test purposes, we will set it here
        token = "0a019fb6-d02b-46c5-9976-28e98e897274"

        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("session-key", token)
        return chain.proceed(builder.build())
    }
}