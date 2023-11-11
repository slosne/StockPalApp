package com.example.stockpalapp.data.repositories

import com.example.stockpalapp.model.Resource
import com.google.firebase.auth.FirebaseUser

//Khan, B. (4. september, 2022). Firebase Authentication using MVVM with Hilt and Coroutines
//https://www.simplifiedcoding.net/firebase-authentication-using-mvvm/


interface AuthRepository {
    val currentUser: FirebaseUser?
    val currentUserId: String
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}