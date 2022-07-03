package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.BuildConfig.BASE_URL
import com.example.data.resource.local.room.AppDatabase
import com.example.data.resource.remote.ApiService
import com.example.data.resource.remote.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    companion object {
        @Singleton
        @Provides
        fun provideAuthApi(
            authInterceptor: AuthInterceptor,
            gson: Gson
        ): ApiService {
            return ApiService.buildApi(BASE_URL, authInterceptor, gson)
        }

        @Provides
        @Singleton
        fun provideAppDatabase(
            @ApplicationContext appContext: Context,
//            dbTypeConverter: DbTypeConverter
        ): AppDatabase =
            Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "app_database"
            )
//                .addTypeConverter(dbTypeConverter)
                .fallbackToDestructiveMigration().build()

        @Singleton
        @Provides
        fun provideGson(): Gson {
            return GsonBuilder().setLenient().create()
        }

        @Provides
        @Singleton
        fun provideAuthSessionDao(db: AppDatabase) = db.authSessionDao()

        @Provides
        @Singleton
        fun providePostsDao(db: AppDatabase) = db.postsDao()

//        @Provides
//        @Singleton
//        fun providePostsLocalDatasource(
//            postDao: PostsDao,
//        ) = PostsLocalDatasource(postDao)
    }

}