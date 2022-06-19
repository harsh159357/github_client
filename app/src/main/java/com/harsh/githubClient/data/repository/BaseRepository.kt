package com.harsh.githubClient.data.repository

import com.harsh.githubClient.util.Result

abstract class BaseRepository {

    companion object {
        const val UNAUTHORIZED = "Unauthorized"
        const val NOT_FOUND = "Not found"
        const val SOMETHING_WENT_WRONG = "Something went wrong"

        fun <T : Any> handleSuccess(data: T): Result<T> {
            return Result.Success(data)
        }

        fun <T : Any> handleException(code: Int): Result<T> {
            val exception = getErrorMessage(code)
            return Result.Error(Exception(exception))
        }

        private fun getErrorMessage(httpCode: Int): String {
            return when (httpCode) {
                401 -> UNAUTHORIZED
                404 -> NOT_FOUND
                else -> SOMETHING_WENT_WRONG
            }
        }
    }
}