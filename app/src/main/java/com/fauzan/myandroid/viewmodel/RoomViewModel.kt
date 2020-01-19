package com.fauzan.myandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.myandroid.model.entity.Post
import com.fauzan.myandroid.model.repository.PostRepository
import kotlinx.coroutines.launch

class RoomViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val _allPost = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _allPost

    fun insertPost(post: Post) {
        viewModelScope.launch {
            postRepository.createPost(post)
        }
    }

    fun getAllPosts() {
        viewModelScope.launch {
            _allPost.value = postRepository.getDbAllPosts()
        }
    }

    fun test() : PostRepository{
        return postRepository
    }
}