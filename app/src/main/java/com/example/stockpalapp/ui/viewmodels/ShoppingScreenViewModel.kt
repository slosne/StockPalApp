package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import com.example.stockpalapp.data.repositories.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingScreenViewModel @Inject constructor(val shoppingListRepository: ShoppingListRepository, recipeRepository: RecipeRepository, pantryRepository: PantryRepository) : ViewModel() {

    val shoppingListProducts = shoppingListRepository.shoppingListProduct

    val recipes = recipeRepository.recipes
    val pantry = pantryRepository.pantry

    fun removeShoppingListProduct(itemID: String) {
        viewModelScope.launch {
            shoppingListRepository.deleteShoppingListProduct(itemID)
        }
    }

    fun AddShoppingListProductToPantry(itemID: String) {

    }

}