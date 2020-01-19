package com.fauzan.myandroid.model.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val tId: Int,
    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    val userId: Int
)