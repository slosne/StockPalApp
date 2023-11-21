package com.example.stockpalapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.model.PantryProduct
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(val pantryRepository: PantryRepository) : ViewModel() {

    val pantryProducts = pantryRepository.pantryProduct

    private val _sortedList: MutableStateFlow<List<PantryProduct>> = MutableStateFlow(emptyList())
    var sortedList: StateFlow<List<PantryProduct>> = _sortedList.asStateFlow()

    private val _category = MutableStateFlow("Frozen")
    var category: StateFlow<String> = _category.asStateFlow()

    private val _searchValue = MutableStateFlow("")
    var searchValue: StateFlow<String> = _searchValue.asStateFlow()

    fun updateList(list: List<PantryProduct>) {
        _sortedList.value = list
    }

    fun updateSearch(search: String) {
        _searchValue.value = search
    }

    var sortPantryByCategory = pantryProducts.map { pantryList ->
        pantryList.filter { it.category == category.value;  }
    }

    fun updatePantryCategorisation() {
        sortPantryByCategory = pantryProducts.map { pantryList ->
            pantryList.filter { it.category == category.value && (it.name.lowercase().contains(searchValue.value.lowercase())) }}
    }

    fun convertStateToCategory(index: Int) {
        if (index == 0) {
            _category.value = "Frozen"
        }
        if (index == 1) {
            _category.value = "Refrigerated"
        }
        if (index == 2) {
            _category.value = "Dry"
        }
        if (index == 3) {
            _category.value = "Other"
        }
    }

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

}