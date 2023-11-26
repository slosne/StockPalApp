package com.example.stockpalapp.presentation.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.R
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import com.example.stockpalapp.data.repositories.ShoppingListRepository
import com.example.stockpalapp.domain.model.product.PantryProduct
import com.example.stockpalapp.domain.model.product.ShoppingListProduct
import com.example.stockpalapp.presentation.utils.internetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingScreenViewModel @Inject constructor(
    val shoppingListRepository: ShoppingListRepository,
    recipeRepository: RecipeRepository,
    val pantryRepository: PantryRepository,
    val internetConnection: internetConnection,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val shoppingListProducts = shoppingListRepository.shoppingListProduct

    val recipes = recipeRepository.recipes
    val pantry = pantryRepository.pantry

    fun removeShoppingListProduct(itemID: String) {
        viewModelScope.launch {
            shoppingListRepository.deleteShoppingListProduct(itemID)
            Toast.makeText(context, context.getString(R.string.item_removed), Toast.LENGTH_SHORT).show()
        }
    }

    fun AddShoppingListProductToPantry(item: ShoppingListProduct) {
        viewModelScope.launch {
            pantryRepository.savePantryProduct(
                PantryProduct(
                name = item.name,
                number = item.number,
                eanNumber = item.eanNumber,
                expDate = item.expDate,
                category = item.category,
                image = item.image
            )
            )
            removeShoppingListProduct(item.id)
            Toast.makeText(context,
                context.getString(R.string.added_to_pantry) , Toast.LENGTH_SHORT).show()
        }
    }

}