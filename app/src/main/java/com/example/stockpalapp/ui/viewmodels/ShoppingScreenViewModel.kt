package com.example.stockpalapp.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import com.example.stockpalapp.data.repositories.ShoppingListRepository
import com.example.stockpalapp.model.PantryProduct
import com.example.stockpalapp.model.ShoppingListProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingScreenViewModel @Inject constructor(
    val shoppingListRepository: ShoppingListRepository,
    recipeRepository: RecipeRepository,
    val pantryRepository: PantryRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val shoppingListProducts = shoppingListRepository.shoppingListProduct

    val recipes = recipeRepository.recipes
    val pantry = pantryRepository.pantry

    fun removeShoppingListProduct(itemID: String) {
        viewModelScope.launch {
            shoppingListRepository.deleteShoppingListProduct(itemID)
            Toast.makeText(context, "Produktet er fjernet", Toast.LENGTH_SHORT).show()
        }
    }

    fun AddShoppingListProductToPantry(item: ShoppingListProduct) {
        viewModelScope.launch {
            pantryRepository.savePantryProduct(PantryProduct(
                name = item.name,
                number = item.number,
                eanNumber = item.eanNumber,
                expDate = item.expDate,
                category = item.category,
                image = item.image
            ))
            removeShoppingListProduct(item.id)
            Toast.makeText(context, "Produktet ble lagt til i Matskapet", Toast.LENGTH_SHORT).show()
        }
    }

}