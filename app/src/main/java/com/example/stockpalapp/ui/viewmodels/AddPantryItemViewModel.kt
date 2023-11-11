package com.example.stockpalapp.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.model.PantryProduct
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddPantryItemViewModel @Inject constructor(val pantryRepository: PantryRepository) : ViewModel() {
    val pantry = pantryRepository.pantry

    fun addPantryProduct(name: String, eanNumber: Int, number: Int, category: String, date: String, context: Context) {
        viewModelScope.launch {
            val expDate = convertStringToTimestamp(date)
            pantryRepository.savePantryProduct(PantryProduct(
                name = name,
                eanNumber = eanNumber,
                number = number,
                category = category,
                expDate = expDate))
        }
        Toast.makeText(context, "Vare lagt til", Toast.LENGTH_SHORT).show()
    }

    fun convertStringToTimestamp(dateAsString: String): Timestamp{
        val dateFormat = SimpleDateFormat("ddMMyy", Locale.getDefault())
        val date = dateFormat.parse(dateAsString)
        return Timestamp(date!!)
    }

}