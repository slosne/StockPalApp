package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.model.PantryProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddPantryItemViewModel @Inject constructor(pantryRepository: PantryRepository) : ViewModel() {
    val pantry = pantryRepository.pantry
    val pantryRepository = pantryRepository

    fun addPantryProduct(name: String, eanNumber: Int, number: Int, category: String, date: String) {
        viewModelScope.launch {
            pantryRepository.savePantryProduct(PantryProduct(name = name, eanNumber = eanNumber, number = number, category = category, expDate = date))
        }
    }
}