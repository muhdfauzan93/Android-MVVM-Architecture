package com.fauzan.myandroid.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fauzan.myandroid.model.dao.PostDao
import com.fauzan.myandroid.model.entity.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val postDao: PostDao
}