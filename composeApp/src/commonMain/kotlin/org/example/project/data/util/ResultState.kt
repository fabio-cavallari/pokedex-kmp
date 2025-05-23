package org.example.project.data.util

sealed class ResultState<out T> {
    data object Loading : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: org.example.project.data.util.Error) : ResultState<Nothing>()
    data class Paging<out T>(val data: T) : ResultState<T>()
    data class PagingError<out T>(
        val data: T,
        val message: String,
        val throwable: Throwable? = null
    ) : ResultState<T>()
}