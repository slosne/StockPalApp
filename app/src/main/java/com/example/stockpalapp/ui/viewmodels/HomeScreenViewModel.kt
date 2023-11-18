package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.stockpalapp.data.repositories.AuthRepository
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

    @HiltViewModel
    class HomeScreenViewModel @Inject constructor(
        recipeRepository: RecipeRepository,
        pantryRepository: PantryRepository,
        authRepository: AuthRepository
        ) : ViewModel() {

        val pantry = pantryRepository.pantry
        val pantryProducts = pantryRepository.pantryProduct
        val currentUser = authRepository.currentUser?.displayName

        val sortedProductsByExpDate = pantryProducts.map { pantryList ->
            pantryList.filter { it.expDate != null }
            pantryList.sortedBy { it.expDate?.seconds }
        }

        val recipes = recipeRepository.recipes


    }