package com.example.stockpalapp.model

//Khan, B. (4. september, 2022). Firebase Authentication using MVVM with Hilt and Coroutines
//https://www.simplifiedcoding.net/firebase-authentication-using-mvvm/


sealed class Resource<out R> {
    data class Success<out R>(val result: R) : Resource<R>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
