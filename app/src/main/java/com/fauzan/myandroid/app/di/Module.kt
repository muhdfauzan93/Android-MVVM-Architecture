package com.fauzan.myandroid.app.di

import android.app.Application
import androidx.room.Room
import com.fauzan.myandroid.BuildConfig
import com.fauzan.myandroid.app.AppDatabase
import com.fauzan.myandroid.model.api.ApiService
import com.fauzan.myandroid.model.dao.PostDao
import com.fauzan.myandroid.model.repository.PostRepository
import com.fauzan.myandroid.model.repository.UserRepository
import com.fauzan.myandroid.viewmodel.RestViewModel
import com.fauzan.myandroid.viewmodel.RoomViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    viewModel { RestViewModel(get()) }
    viewModel { RoomViewModel(get()) }
}


val apiModule = module {
    fun provideRetrofitApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { provideRetrofitApi(get()) }
}

val netModule = module {

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        if (BuildConfig.DEBUG) okHttpClientBuilder.addInterceptor(logging)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase): PostDao {
        return database.postDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }

    fun providePostRepository(apiService: ApiService, postDao: PostDao): PostRepository {
        return PostRepository(apiService, postDao)
    }

    single { provideUserRepository(get()) }
    single { providePostRepository(get(), get()) }

}