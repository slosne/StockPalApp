package com.example.stockpalapp.ui.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.ProductRepository
import com.example.stockpalapp.model.PantryProduct
import com.example.stockpalapp.model.Product
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddPantryItemViewModel @Inject constructor(
    val pantryRepository: PantryRepository,
    val productRepository: ProductRepository
) : ViewModel() {
    val pantry = pantryRepository.pantry

    fun addPantryProduct(name: String, eanNumber: Int, number: Int, category: String, date: String, context: Context, imgUrl: String) {
        viewModelScope.launch {
            val expDate = convertStringToTimestamp(date)
            pantryRepository.savePantryProduct(PantryProduct(
                name = name,
                eanNumber = eanNumber,
                number = number,
                category = category,
                expDate = expDate,
                image = imgUrl))
        }
        Toast.makeText(context, "Vare lagt til", Toast.LENGTH_SHORT).show()
    }

    fun convertStringToTimestamp(dateAsString: String): Timestamp{
        val dateFormat = SimpleDateFormat("ddMMyy", Locale.getDefault())
        val date = dateFormat.parse(dateAsString)
        return Timestamp(date!!)
    }

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> get() = _product

    fun getAPorudctByEanNumber(seachInput: String) {
        viewModelScope.launch {
            _product.value = productRepository.getProductByEanNumber(seachInput)
            Log.d("ViewModelProduct", _product.value.toString())
            Log.d("Test1", _product.value?.name.toString())
        }
    }

}