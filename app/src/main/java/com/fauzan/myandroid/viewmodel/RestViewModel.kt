package com.fauzan.myandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.myandroid.model.entity.Response
import com.fauzan.myandroid.model.entity.Status
import com.fauzan.myandroid.model.entity.User
import com.fauzan.myandroid.model.repository.UserRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var allUsersData = MutableLiveData<Response<List<User>>>()
    private var userData = MutableLiveData<Response<User>>()

    private val apiErrorHandler = CoroutineExceptionHandler { _, throwable ->
        allUsersData.value = Response(Status.ERROR, null, throwable)
    }

    fun fetchAllUserData() {
        viewModelScope.launch(apiErrorHandler) {
            allUsersData.value = Response(Status.LOADING, null, null)
            val result = withContext(Dispatchers.IO) {
                userRepository.getAllUsers()
            }

            if (result.isSuccessful) {
                allUsersData.value = Response(Status.SUCCESS, result.body(), null)
            } else {
                allUsersData.value =
                    Response(Status.ERROR, null, Throwable(result.code().toString()))
            }
        }
    }

    fun fetchUserData(id: Int) {
        viewModelScope.launch(apiErrorHandler) {
            userData.value = Response(Status.LOADING, null, null)
            val result = withContext(Dispatchers.IO) {
                userRepository.getUser(id)
            }

            if (result.isSuccessful) {
                userData.value = Response(Status.SUCCESS, result.body(), null)
            } else {
                userData.value = Response(Status.ERROR, null, Throwable(result.code().toString()))
            }
        }
    }

    fun getAllUsersData() = allUsersData as LiveData<Response<List<User>>>
    fun getUserData() = userData as LiveData<Response<User>>

}