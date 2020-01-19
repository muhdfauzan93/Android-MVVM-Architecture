package com.fauzan.myandroid.model.repository

import com.fauzan.myandroid.model.api.ApiService
import com.fauzan.myandroid.model.dao.PostDao
import com.fauzan.myandroid.model.entity.Post
import retrofit2.Response

class PostRepository(private val apiService: ApiService, private val postDao: PostDao) {
    suspend fun getAllPosts(): Response<List<Post>> = apiService.getUserPosts()
    suspend fun getDbAllPosts(): List<Post> = postDao.getAll()
    suspend fun getPostsById(id:Int):List<Post> = postDao.findByUserId(id)
    suspend fun createPost(post: Post) = postDao.insertPost(post)
    suspend fun updatePost(post: Post) = postDao.updatePost(post)
    suspend fun deletePost(post: Post) = postDao.deletePost(post)
}