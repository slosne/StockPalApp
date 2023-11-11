package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockpalapp.ui.components.FilledBtn
import com.example.stockpalapp.ui.viewmodels.AddPantryItemViewModel

@Composable
fun AddPantryItemScanning() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        FilledBtn(clickHandler = { /*TODO*/ }, btnText = stringResource(R.string.scan_item))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItem() {
    val addPantryItemViewModel: AddPantryItemViewModel = hiltViewModel()
    var expandedState by remember { mutableStateOf(true)}

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
        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
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
                    OutlinedTextField(
                        value = title,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newTitle ->
                            title = newTitle
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
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var ean by remember { mutableStateOf("")}
                    Text(text = stringResource(R.string.required), color = Color.Red, fontSize = 12.sp)
                    OutlinedTextField(
                        value = ean,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newEan ->
                            ean = newEan
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
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var ammount by remember { mutableStateOf("")}
                    OutlinedTextField(
                        value = ammount,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newAmmount ->
                            ammount = newAmmount
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
                    )



                    Spacer(modifier = Modifier.size(15.dp))
                    var category by remember { mutableStateOf("")}
                    OutlinedTextField(
                        value = category,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newCategory ->
                            category = newCategory
                        },
                        label = {
                            Text(text = stringResource(R.string.category))
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var expDate by remember { mutableStateOf("")}
                    OutlinedTextField(
                        value = expDate,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newExpDate ->
                            expDate = newExpDate
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
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var imgUrl by remember { mutableStateOf("")}
                    Text(text = stringResource(R.string.not_required), color = Color.Green, fontSize = 12.sp)
                    OutlinedTextField(
                        value = imgUrl,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newImgUrl ->
                            imgUrl = newImgUrl
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
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){
                        FilledBtn(clickHandler = { addPantryItemViewModel.addPantryProduct(title, ean.toInt(), ammount.toInt(), category, expDate, context, imgUrl)
                            title = ""
                            ammount = ""
                            category = ""
                            expDate = ""
                            ean = ""
                            imgUrl = ""}, btnText = stringResource(R.string.save))
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
                    AddPantryItemScanning()
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