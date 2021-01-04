package com.epam.messagelocal.ui

sealed class LoadingState

object Loading : LoadingState()
data class Loaded<T>(val data: T) : LoadingState()
data class Error(val message: String) : LoadingState()