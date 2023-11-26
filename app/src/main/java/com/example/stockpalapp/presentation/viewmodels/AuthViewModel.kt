package com.example.stockpalapp.presentation.viewmodels

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.AuthRepository
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.domain.model.Pantry
import com.example.stockpalapp.domain.model.Resource
import com.example.stockpalapp.domain.usecase.InputValidation
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val pantryRepository: PantryRepository,
    private val inputValidation: InputValidation
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    fun signupUser(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result

        createPantryAfterSignUp(name = name)
    }

    fun logout() {
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }

    fun isNameValid(name: String): Boolean {
        return inputValidation.isNameValid(name)
    }

    fun isPasswordValid(password: String): Boolean {
        return inputValidation.isPasswordValid(password)
    }

    fun createPantryAfterSignUp(name: String) {
        viewModelScope.launch {
            pantryRepository.savePantry(Pantry(id = repository.currentUserId, name = name), itemID = repository.currentUserId)
        }
    }

    fun handleSignUpClick(name: String, email: String, password: String, context: Context) {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        } else if (!isNameValid(name)) {
            Toast.makeText(context, "Name must not include special characters", Toast.LENGTH_SHORT).show()
            Log.e("AuthViewModel", "Invalid name format")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
        } else if (!isPasswordValid(password)){
            Toast.makeText(context, "Password must contain at least 6 chars and include 1 digit", Toast.LENGTH_SHORT).show()
        }
        else {
            signupUser(name, email, password)
        }
    }
}