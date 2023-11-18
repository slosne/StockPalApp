package com.example.stockpalapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.components.FilledBtn
import com.example.stockpalapp.ui.model.categories
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.AddPantryItemViewModel
import com.example.stockpalapp.ui.visualTransformation.DateVisualTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItemScanningAndSearching() {
    val addPantryItemViewModel: AddPantryItemViewModel = hiltViewModel()
    val product by addPantryItemViewModel.product.collectAsState()

    val scannedBarcode by addPantryItemViewModel.scannedBarcode.collectAsState()

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            FilledBtn(clickHandler = { addPantryItemViewModel.ScanningAProduct() }, btnText = stringResource(R.string.scan_item))
        }

        if (scannedBarcode != null) {
            Text(text = scannedBarcode!!)
    }

        var seachInput by remember { mutableStateOf("")}
        var seachInputChange by remember { mutableStateOf(0)}

        OutlinedTextField(
            value = seachInput,
            shape = TextFieldDefaults.outlinedShape,
            onValueChange = { newTitle ->
                seachInput = newTitle
                seachInputChange = 1
                if (seachInput != null) {
                    addPantryItemViewModel.getAPorudctByEanNumber(seachInput)
                    Log.d("TAG", product.toString())
                }

            },
            label = {
                Text(text = "Søkefelt")
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
        )
        // Check if product is not null before accessing its properties
        if (product != null) {
            Text(text = product!!.name)
            // Add other Text or Composables to display other product details

            var title = product!!.name
            var ean = product!!.eanNumber
            var ammount = product!!.number
            var category = product!!.category
            var expDate = "050398"
            var context = LocalContext.current
            var imgUrl = product!!.image

            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){
                FilledBtn(clickHandler = { addPantryItemViewModel.addPantryProduct(title, ean.toInt(), ammount.toInt(), category, expDate, context, imgUrl)
                    title = ""
                    ammount = 0
                    category = ""
                    expDate = ""
                    ean = ""
                    imgUrl = ""}, btnText = stringResource(R.string.save))
            }
        } else {
            Text(text = "No product found")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItem() {
    val addPantryItemViewModel: AddPantryItemViewModel = hiltViewModel()
    var expandedState by remember { mutableStateOf(false)}

    val context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 10.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState())
            ) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = stringResource(R.string.add_pantryitem)
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { expandedState = !expandedState }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop-Down Arrow")
                }
            }
            Spacer(modifier = Modifier.size(15.dp))

            if(expandedState) {
                Column {
                    var title by remember { mutableStateOf("")}
                    var isTitleError by remember { mutableStateOf(false)}

                    OutlinedTextField(
                        value = title,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newTitle ->
                            title = newTitle
                            isTitleError = !addPantryItemViewModel.isValidProductName(title)
                        },
                        label = {
                            Text(text = stringResource(R.string.item_name))
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        isError = title.isNotEmpty() && !addPantryItemViewModel.isValidProductName(title),
                        supportingText = {

                            if (title.isEmpty()) {
                                Text(text = "Obligatorisk - Input kan ikke være tom")
                            } else if (isTitleError && !addPantryItemViewModel.isValidProductName(title)) {
                                Text(text = "Kan bare inneholde tall, bokstaver. Og maks 25 karakterer")
                            }
                        }
                        
                    )



                    Spacer(modifier = Modifier.size(15.dp))
                    var ean by remember { mutableStateOf("")}
                    var isEanError by remember { mutableStateOf(false)}
                    OutlinedTextField(
                        value = ean,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newEan ->
                            ean = newEan
                            isEanError = !addPantryItemViewModel.isValidEanNumber(ean)
                        },
                        label = {
                            Text(text = stringResource(R.string.ean))
                        },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        isError = ean.isNotEmpty() && !addPantryItemViewModel.isValidEanNumber(ean),
                        supportingText = {

                            if (ean.isEmpty()) {
                                Text(text = "Obligatorisk - Input kan ikke være tom")
                            } else if (isEanError && !addPantryItemViewModel.isValidEanNumber(ean)) {
                                Text(text = "Kan bare inneholde tall")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var ammount by remember { mutableStateOf("")}
                    var isAmmountError by remember { mutableStateOf(false)}
                    OutlinedTextField(
                        value = ammount,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newAmmount ->
                            ammount = newAmmount
                            isAmmountError = !addPantryItemViewModel.isValidAmmount(ammount)
                        },
                        label = {
                            Text(text = stringResource(R.string.ammount))
                        },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        isError = ammount.isNotEmpty() && !addPantryItemViewModel.isValidAmmount(ammount),
                        supportingText = {

                            if (ammount.isEmpty()) {
                                Text(text = "Obligatorisk - Input kan ikke være tom")
                            } else if (isAmmountError && !addPantryItemViewModel.isValidAmmount(ammount)) {
                                Text(text = "Kan bare inneholde tall")
                            }
                        }
                    )



                    Spacer(modifier = Modifier.size(15.dp))

                    val options = categories
                    var category by remember { mutableStateOf(options[0]) }
                    var expanded by remember { mutableStateOf(false)}

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded}
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            value = category,
                            onValueChange = { newCategory ->
                                category = newCategory
                            },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        )
                        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            options.forEach {categorySelect ->
                                DropdownMenuItem(text = { Text(categorySelect) },
                                    onClick = {
                                        category = categorySelect
                                        expanded = false },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(15.dp))
                    var expDate by remember { mutableStateOf("")}
                    var expDateShow by remember { mutableStateOf("")}
                    var isDateError by remember { mutableStateOf(false)}
                    var isDateLength by remember { mutableStateOf(false)}
                    OutlinedTextField(
                        value = expDate,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newExpDate ->
                            expDateShow = newExpDate.filter { it.isDigit() }

                            expDate = expDateShow.take(6)

                            Log.d("expDateShow", expDateShow)
                            Log.d("expDate", expDate)
                            isDateError = !addPantryItemViewModel.isValidDatePicker(expDate)
                            isDateLength = expDate.length >= 6
                        },
                        label = {
                            Text(text = stringResource(R.string.exp_date))
                        },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        visualTransformation = DateVisualTransformation(),
                        isError = expDate.isNotEmpty() && !addPantryItemViewModel.isValidDatePicker(expDate),
                        supportingText = {
                            if (expDate.isEmpty()) {
                                Text(text = "Obligatorisk - Input kan ikke være tom")
                            } else if (expDate.length != 6 ) {
                                Text(text = "Dato må være i fromat DD/MM/YY")
                            }
                        }
                    )



                    Spacer(modifier = Modifier.size(15.dp))
                    var imgUrl by remember { mutableStateOf("")}
                    var isImgURLError by remember { mutableStateOf(false) }
                    Text(text = stringResource(R.string.not_required), color = Color.Green, fontSize = 12.sp)
                    OutlinedTextField(
                        value = imgUrl,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newImgUrl ->
                            imgUrl = newImgUrl
                            isImgURLError = !addPantryItemViewModel.isValidImageUrl(imgUrl)
                        },
                        label = {
                            Text(text = stringResource(R.string.img_url))
                        },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Uri,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        isError = imgUrl.isNotEmpty() && !addPantryItemViewModel.isValidImageUrl(imgUrl),
                        supportingText = {

                            if (isImgURLError && !addPantryItemViewModel.isValidImageUrl(imgUrl)) {
                                Text(text = "http, https, ftp må brukes. Må være en gyldig url")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){
                        FilledBtn(clickHandler = { addPantryItemViewModel.addPantryProduct(title, ean.toInt(), ammount.toInt(), category, expDate, context, imgUrl)
                            title = ""
                            ammount = ""
                            category = ""
                            expDate = ""
                            ean = ""
                            imgUrl = ""},
                            btnText = stringResource(R.string.save),
                            enabled = !isTitleError && title.isNotEmpty() && !isEanError && ean.isNotEmpty() && !isAmmountError && ammount.isNotEmpty() && !isDateError && isDateLength && !isImgURLError
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItemScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){
    StockPalAppTheme {
        Surface(tonalElevation = 5.dp) {
            AppLayout(content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    Spacer(modifier = Modifier.size(10.dp))
                    AddPantryItemScanningAndSearching()
                    AddPantryItem()
                }
            },
                topAppBarTitle = stringResource(R.string.add_pantryitem),
                navigationIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Menu,
                navigationContentDescription = stringResource(R.string.navigate_up),
                actionContentDescription = stringResource(R.string.navigation_drawer),
                navController = navController,
                navigationClickHandler = {navController.navigateUp()},
                scope = scope,
                drawerState = drawerState,
                arrowBackClickHandler = {scope.launch { drawerState.open() }}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddPantryItemScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        AddPantryItemScreen(navController, drawerState, scope)
    }
}