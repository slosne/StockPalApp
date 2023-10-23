package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.stockpalapp.data.repositories.PantryRepository
import javax.inject.Inject

class AddPantryItemViewModel @Inject constructor(pantryRepository: PantryRepository) : ViewModel() {
    val pantry = pantryRepository.pantry
}