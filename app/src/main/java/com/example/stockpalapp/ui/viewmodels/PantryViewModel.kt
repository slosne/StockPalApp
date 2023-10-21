package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.stockpalapp.data.repositories.PantryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(pantryRepository: PantryRepository) : ViewModel() {

    val pantry = pantryRepository.pantry

}