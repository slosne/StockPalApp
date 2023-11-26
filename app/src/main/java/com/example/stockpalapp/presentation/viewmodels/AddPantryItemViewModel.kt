package com.example.stockpalapp.presentation.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockpalapp.R
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.ProductRepository
import com.example.stockpalapp.data.repositories.ShoppingListRepository
import com.example.stockpalapp.domain.model.product.PantryProduct
import com.example.stockpalapp.domain.model.product.Product
import com.example.stockpalapp.domain.model.product.ShoppingListProduct
import com.example.stockpalapp.domain.usecase.InputValidation
import com.example.stockpalapp.domain.usecase.TimeConverter
import com.example.stockpalapp.presentation.utils.BarcodeScanning
import com.example.stockpalapp.presentation.utils.internetConnection
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddPantryItemViewModel @Inject constructor(
    val pantryRepository: PantryRepository,
    val productRepository: ProductRepository,
    val shoppingListRepository: ShoppingListRepository,
    val timeConverter: TimeConverter,
    val barcodeScanning: BarcodeScanning,
    val inputValidation: InputValidation,
    val internetConnection: internetConnection,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val pantry = pantryRepository.pantry

    fun addPantryProduct(name: String, eanNumber: Long, number: Int, category: String, date: String, context: Context, imgUrl: String) {
        viewModelScope.launch {
            val expDate = convertStringToTimestamp(date)
            pantryRepository.savePantryProduct(
                PantryProduct(
                name = name,
                eanNumber = eanNumber,
                number = number,
                category = category,
                expDate = expDate,
                image = imgUrl)
            )
        }
        if (internetConnection.isInternetAvailable(context)){
            Toast.makeText(context, context.getString(R.string.item_added), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_LONG).show()
        }

    }

    fun addShoppingListProduct(name: String, eanNumber: Long, number: Int, category: String, context: Context, imgUrl: String) {
        viewModelScope.launch {
            shoppingListRepository.saveShoppingListProduct(
                ShoppingListProduct(
                name = name,
                eanNumber = eanNumber,
                number = number,
                category = category,
                image = imgUrl
            )
            )
        }
        if (internetConnection.isInternetAvailable(context)){
            Toast.makeText(context, context.getString(R.string.item_added), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, context.getString (R.string.no_internet), Toast.LENGTH_LONG).show()
        }
    }


    //ListOfProductsToAdd

    // StateFlow to hold the list of ShoppingListProduct
    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> get() = _productList

    fun addProductToList(item: Product) {
        viewModelScope.launch {
            _productList.value = _productList.value + item.copy()
            Log.d("List", _productList.value.toString())
            Toast.makeText(context,
                context.getString(R.string.added_to_itemlist), Toast.LENGTH_SHORT).show()
        }
    }

    fun addMultipleShoppingListProduct(){
        viewModelScope.launch {
            shoppingListRepository.saveMultipleShoppingListProducts(_productList.value)
            _productList.value = emptyList()
            if (internetConnection.isInternetAvailable(context)){
                Toast.makeText(context, context.getString (R.string.added_to_shopping), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString (R.string.no_internet), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun addMultiplePantryProduct() {
        viewModelScope.launch {
            pantryRepository.saveMultiplePantryProducts(_productList.value)
            _productList.value = emptyList()
            if (internetConnection.isInternetAvailable(context)){
                Toast.makeText(context,
                    context.getString(R.string.added_to_pantry), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString (R.string.no_internet), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun convertStringToTimestamp(dateAsString: String): Timestamp{
        return timeConverter.convertStringToTimestamp(dateAsString)
    }

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> get() = _product

    fun getAProductByEanNumber(seachInput: Long) {
        viewModelScope.launch {
            _product.value = productRepository.getProductByEanNumber(seachInput)
        }
    }

    fun getAProductByEanNumberAndAddToList(seachInput: Long) {
        viewModelScope.launch {
            var productFromData = productRepository.getProductByEanNumber(seachInput)
            if (productFromData !=null) {
                addProductToList(Product(id = productFromData.id, name = productFromData.name, eanNumber = productFromData.eanNumber, number = 1, category = productFromData.category, image = productFromData.image, expDate = Timestamp(
                    Date(2025)
                ), vendor = productFromData.vendor))
            } else {
                Toast.makeText(context,
                    context.getString(R.string.not_in_database), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun ScanningAProduct() {
        barcodeScanning.scanner.startScan()
            .addOnSuccessListener { barcode ->
                // Task completed successfully
                Log.d("ScnInp", barcode.toString())
                barcodeScanning._scannedBarcode.value = barcode.rawValue.toString()
                val barcodeInput = barcode.rawValue!!.toString().toLong()
                getAProductByEanNumberAndAddToList(barcodeInput)
            }
            .addOnCanceledListener {
                // Task canceled
                Toast.makeText(context,
                    context.getString(R.string.scan_cancelled), Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Toast.makeText(context, context.getString (R.string.scan_failed), Toast.LENGTH_SHORT).show()
            }
    }

    fun isValidProductName(text: String): Boolean {
        return inputValidation.isValidProductName(text)
    }

    fun isValidEanNumber(text: String): Boolean {
        return inputValidation.isValidEanNumber(text)
    }

    fun isValidAmmount(text: String): Boolean {
        return inputValidation.isValidAmmount(text)
    }

    fun isValidDatePicker(text: String): Boolean {
        return inputValidation.isValidDatePicker(text)
    }

    fun isValidImageUrl(url: String): Boolean {
        return inputValidation.isValidImageUrl(url)
    }


}