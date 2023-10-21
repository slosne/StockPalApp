package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.Datasource
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(recipeRepository: RecipeRepository, pantryRepository: PantryRepository) : ViewModel() {

    val pantry = pantryRepository.pantry
    val recipes = recipeRepository.recipes


}