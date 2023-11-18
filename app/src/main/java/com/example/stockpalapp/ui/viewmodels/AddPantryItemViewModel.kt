package com.example.stockpalapp.ui.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.ProductRepository
import com.example.stockpalapp.model.PantryProduct
import com.example.stockpalapp.model.Product
import com.google.firebase.Timestamp
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddPantryItemViewModel @Inject constructor(
    val pantryRepository: PantryRepository,
    val productRepository: ProductRepository,
    @ApplicationContext private val context: Context
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


    //Scanning Flow
    private val _scannedBarcode = MutableStateFlow<String?>(null)
    val scannedBarcode: StateFlow<String?> = _scannedBarcode


    //Scanning funksjonalitet
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_ALL_FORMATS
        )
        .enableAutoZoom()
        .build()

    val scanner = GmsBarcodeScanning.getClient(context, options)

    fun ScanningAProduct() {
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                // Task completed successfully
                Log.d("ScnInp", barcode.toString())
                _scannedBarcode.value = barcode.rawValue.toString()
            }
            .addOnCanceledListener {
                // Task canceled
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
            }
    }


    //Input Validation TODO: Kanskje flytt til use cases

    fun isValidProductName(text: String): Boolean {
        return text.matches(Regex("[a-zA-Z0-9,]{1,25}"))
    }

    fun isValidEanNumber(text: String): Boolean {
        return text.matches(Regex("[0-9]+"))
    }

    fun isValidNumber(text: String): Boolean {
        return text.matches(Regex("[0-9]+"))
    }

    fun isValidExpDate(text: String): Boolean {
        return text.matches(Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{2}$"))
    }

    fun isValidDate(text: String): Boolean {
        return text.matches(Regex("[0-9/]{1,8}"))
    }
}