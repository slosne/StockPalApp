package com.example.stockpalapp.ui.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.ProductRepository
import com.example.stockpalapp.data.repositories.ShoppingListRepository
import com.example.stockpalapp.model.PantryProduct
import com.example.stockpalapp.model.Product
import com.example.stockpalapp.model.ShoppingListProduct
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
    val shoppingListRepository: ShoppingListRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val pantry = pantryRepository.pantry

    fun addPantryProduct(name: String, eanNumber: Long, number: Int, category: String, date: String, context: Context, imgUrl: String) {
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

    fun addShoppingListProduct(name: String, eanNumber: Long, number: Int, category: String, context: Context, imgUrl: String) {
        viewModelScope.launch {
            shoppingListRepository.saveShoppingListProduct(ShoppingListProduct(
                name = name,
                eanNumber = eanNumber,
                number = number,
                category = category,
                image = imgUrl
            ))
        }
        Toast.makeText(context, "Vare lagt til", Toast.LENGTH_SHORT).show()
    }


    //ListOfProductsToAdd

    // StateFlow to hold the list of ShoppingListProduct
    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> get() = _productList

    fun addProductToList(item: Product) {
        viewModelScope.launch {
            _productList.value = _productList.value + item.copy()
            Log.d("List", _productList.value.toString())
        }
    }

    fun addMultipleShoppingListProduct(){
        viewModelScope.launch {
            shoppingListRepository.saveMultipleShoppingListProducts(_productList.value)
            _productList.value = emptyList()
        }
    }

    fun addMultiplePantryProduct() {
        viewModelScope.launch {
            pantryRepository.saveMultiplePantryProducts(_productList.value)
            _productList.value = emptyList()
        }
    }

    fun convertStringToTimestamp(dateAsString: String): Timestamp{
        val dateFormat = SimpleDateFormat("ddMMyy", Locale.getDefault())
        val date = dateFormat.parse(dateAsString)
        return Timestamp(date!!)
    }

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> get() = _product

    fun getAProductByEanNumber(seachInput: Long) {
        viewModelScope.launch {
            _product.value = productRepository.getProductByEanNumber(seachInput)
            Log.d("ViewModelProduct", _product.value.toString())
            Log.d("Test1", _product.value?.name.toString())
        }
    }

    fun getAProductByEanNumberAndAddToList(seachInput: Long) {
        viewModelScope.launch {
            var productFromData = productRepository.getProductByEanNumber(seachInput)
            if (productFromData !=null) {
                addProductToList(productFromData)
            }
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
                val barcodeInput = barcode.rawValue!!.toString().toLong()
                getAProductByEanNumberAndAddToList(barcodeInput)
            }
            .addOnCanceledListener {
                // Task canceled
                Toast.makeText(context, "Scanning kanselert", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Toast.makeText(context, "Scanning Feilet", Toast.LENGTH_SHORT).show()
            }
    }


    //Input Validation TODO: Kanskje flytt til use cases

    fun isValidProductName(text: String): Boolean {
        return text.matches(Regex("[a-zA-Z0-9,æøåÆØÅ\\s]{1,25}"))
    }

    fun isValidEanNumber(text: String): Boolean {
        return text.matches(Regex("[0-9]+"))
    }

    fun isValidAmmount(text: String): Boolean {
        return text.matches(Regex("[0-9]+"))
    }

    fun isValidDatePicker(text: String): Boolean {
        return text.matches(Regex("[0-9/]{1,8}"))
    }

    fun isValidImageUrl(url: String): Boolean {
        return url.matches(Regex("""^(https?|ftp):\/\/[^\s\/$.?#].[^\s]*$"""))
    }


}