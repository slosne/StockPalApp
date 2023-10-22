package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingScreenViewModel @Inject constructor(recipeRepository: RecipeRepository, pantryRepository: PantryRepository) : ViewModel() {

    val recipes = recipeRepository.recipes
    val pantry = pantryRepository.pantry

}