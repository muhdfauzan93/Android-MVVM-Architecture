package com.fauzan.myandroid.model.repository

import com.fauzan.myandroid.model.api.ApiService
import com.fauzan.myandroid.model.entity.User
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {
    suspend fun getUser(id: Int): Response<User> = apiService.getUser(id)
    suspend fun getAllUsers(): Response<List<User>> = apiService.getAllUsers()
}