package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.model.PantryProduct
import com.google.firebase.firestore.DocumentId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(pantryRepository: PantryRepository) : ViewModel() {

    val pantryProducts = pantryRepository.pantryProduct

    //val pantryProducts: LiveData<<List<PantryProducts>

    //val pantryProducts = pantryRepository.getPantryProducts("55wGgQdBnqyVnA8cIc08")

}