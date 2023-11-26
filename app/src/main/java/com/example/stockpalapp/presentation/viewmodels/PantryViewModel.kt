package com.example.stockpalapp.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.domain.model.product.PantryProduct
import com.example.stockpalapp.domain.usecase.TimeConverter
import com.example.stockpalapp.presentation.utils.internetConnection
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(val pantryRepository: PantryRepository, val timeConverter: TimeConverter, val internetConnection: internetConnection, @ApplicationContext private val context: Context) : ViewModel() {

    val pantryProducts = pantryRepository.pantryProduct

    private val _sortedList: MutableStateFlow<List<PantryProduct>> = MutableStateFlow(emptyList())
    var sortedList: StateFlow<List<PantryProduct>> = _sortedList.asStateFlow()

    private val _category = MutableStateFlow("Frysevarer")
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
        pantryList.filter { it.category == category.value && (it.name.lowercase().contains(searchValue.value.lowercase()))  }
    }

    fun updatePantryCategorisation() {
         sortPantryByCategory = pantryProducts.map { pantryList ->
            pantryList.filter { it.category == category.value && (it.name.lowercase().contains(searchValue.value.lowercase())) }}
    }

    fun convertStateToCategory(index: Int) {
        if (index == 0) {
            _category.value = "Frysevarer"
        }
        if (index == 1) {
            _category.value = "Kjølevarer"
        }
        if (index == 2) {
            _category.value = "Tørrvarer"
        }
        if (index == 3) {
            _category.value = "Annet"
        }
    }

    fun convertTimestampToString(timestamp: Timestamp): String {
        return timeConverter.convertTimestampToString(timestamp)
    }

    fun removePantryProduct(itemId: String) {
        viewModelScope.launch {
            pantryRepository.deletePantryProduct(itemId)
        }
    }

    fun addPantryProduct(item: PantryProduct) {
        viewModelScope.launch {
            pantryRepository.savePantryProduct(item)
        }
    }

    fun updatePantryProduct(itemId: String, updatedProduct: PantryProduct) {
        viewModelScope.launch {
            pantryRepository.updatePantryProduct(itemId, updatedProduct)
        }
    }

    fun updatePantryProductByAddingANumber(itemId: String, product: PantryProduct) {
        Log.d("Tasting", product.number.toString())
        var editingProduct = product

        editingProduct.number = editingProduct.number + 1
        Log.d("Tasting", editingProduct.number.toString())

        updatePantryProduct(itemId, editingProduct)

    }

    fun updatePantryProductBySubtractingAnumber(itemId: String, product: PantryProduct){
        Log.d("Tasting", product.number.toString())
        var editingProduct = product

        editingProduct.number = editingProduct.number - 1
        Log.d("Tasting", editingProduct.number.toString())

        updatePantryProduct(itemId, editingProduct)
    }

}