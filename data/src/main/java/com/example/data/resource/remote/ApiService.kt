package com.example.data.resource.remote

import com.example.data.BuildConfig
import com.example.data.resource.remote.response.ResponseErrorMessage
import com.example.data.resource.remote.response.posts.PostsResponse
import com.google.gson.Gson
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("/v4/feed/consolidated/posts")
    suspend fun getPosts(
        @Query("count") count: Int,
        @Query("start") start: Int
    ): NetworkResponse<PostsResponse, ResponseErrorMessage>

    @GET("/v4/feed/consolidated/meta")
    suspend fun getMeta(
    ): NetworkResponse<PostsResponse, ResponseErrorMessage>

    companion object {
        fun buildApi(
            baseUrl: String,
            authInterceptor: AuthInterceptor,
            gson: Gson
        ): ApiService {

            val okHttpClient = OkHttpClient.Builder()
                .also { client ->
                    client.addInterceptor(authInterceptor)
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}