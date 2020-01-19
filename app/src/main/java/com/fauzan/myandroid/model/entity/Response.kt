package com.fauzan.myandroid.model.entity

data class Response<out T> (
    val status: Status,
    val data: T?,
    val error: Throwable?
)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}