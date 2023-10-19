package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(private val pantryRepository: PantryRepository) : ViewModel() {

    val pantry = pantryRepository.pantry

}