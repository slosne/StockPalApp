package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockpalapp.data.repositories.PantryRepository
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(val pantryRepository: PantryRepository) : ViewModel() {

    val pantryProducts = pantryRepository.pantryProduct

    fun convertTimestampToString(timestamp: Timestamp): String {
        val instant = Instant.ofEpochSecond(timestamp.seconds)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return localDateTime.format(formatter)
    }

    fun removePantryProduct(itemId: String) {
        viewModelScope.launch {
            pantryRepository.deletePantryProduct(itemId)
        }
    }

    //val pantryProducts: LiveData<<List<PantryProducts>

    //val pantryProducts = pantryRepository.getPantryProducts("55wGgQdBnqyVnA8cIc08")

}