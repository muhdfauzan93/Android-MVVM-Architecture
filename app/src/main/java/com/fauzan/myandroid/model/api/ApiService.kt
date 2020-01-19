package com.fauzan.myandroid.model.api

import com.fauzan.myandroid.model.entity.Post
import com.fauzan.myandroid.model.entity.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/users/{Id}")
    suspend fun getUser(@Path("Id") id: Int): Response<User>

    @GET("/users")
    suspend fun getAllUsers(): Response<List<User>>

    @GET("/posts")
    suspend fun getUserPosts(): Response<List<Post>>
}