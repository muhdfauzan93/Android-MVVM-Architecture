package com.fauzan.myandroid.model.dao

import androidx.room.*
import com.fauzan.myandroid.model.entity.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    suspend fun getAll(): List<Post>

    @Query("SELECT * FROM post WHERE userId LIKE:userId")
    fun findByUserId(userId: Int): List<Post>

    @Insert
    suspend fun insertPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)
}