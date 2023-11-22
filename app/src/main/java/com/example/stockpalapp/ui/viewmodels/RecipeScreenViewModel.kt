package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import com.example.stockpalapp.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class RecipeScreenViewModel @Inject constructor(recipeRepository: RecipeRepository, pantryRepository: PantryRepository) : ViewModel() {

    val recipes = recipeRepository.recipes
    val pantry = pantryRepository.pantry

    private val _sortedList: MutableStateFlow<List<Recipe>> = MutableStateFlow(emptyList())
    var sortedList: StateFlow<List<Recipe>> = _sortedList.asStateFlow()

    private val _searchValue = MutableStateFlow("")
    var searchValue: StateFlow<String> = _searchValue.asStateFlow()

    fun updateList(list: List<Recipe>) {
        _sortedList.value = list
    }

    fun updateSearch(search: String) {
        _searchValue.value = search
    }

    fun updatePantryCategorisation() {
        sortRecipeByName = recipes.map { pantryList ->
            pantryList.filter { it.title.lowercase().contains(searchValue.value.lowercase()) }}
    }

    var sortRecipeByName = recipes.map { recipeList ->
        recipeList.filter { it.title.lowercase().contains(searchValue.value.lowercase())  }
    }

}
