package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.components.FullRecipeCard
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.RecipeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun RecipeSearch(){

    val recipeScreenViewModel: RecipeScreenViewModel = hiltViewModel()
    val recipeProducts by recipeScreenViewModel.sortRecipeByName.collectAsState(initial = emptyList())
    recipeScreenViewModel.updateList(recipeProducts)

    var searchValue by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center){
        OutlinedTextField(
            value = searchValue,
            onValueChange = {
                searchValue = it
                recipeScreenViewModel.updateSearch(searchValue)
                recipeScreenViewModel.updatePantryCategorisation()
                recipeScreenViewModel.updateList(recipeProducts)},
            shape = OutlinedTextFieldDefaults.shape,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        )
    }
}

@Composable
fun RecipePage(){

    val recipeScreenViewModel: RecipeScreenViewModel = hiltViewModel()
    val sortedList = recipeScreenViewModel.sortedList.collectAsState().value

    LazyColumn {
        item {
            RecipeSearch()
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth())
            {
                Spacer(modifier = Modifier.size(15.dp))
                Text(text = stringResource(R.string.suggestions),
                    style = MaterialTheme
                        .typography
                        .titleLarge)
                Spacer(modifier = Modifier.size(15.dp))
            }
        }
        items(sortedList){ item ->
            FullRecipeCard(
                title = item.title,
                imageUrl = item.image,
                cuisine = item.cuisine,
                cookingTime = item.cookingTime,
                ingredients = item.ingredients,
                instructions = item.instructions
            )
        }
    }
}

@Composable
fun RecipeScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope) {

    AppLayout(
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues))
            {
                Spacer(modifier = Modifier.size(20.dp))
                RecipePage()
            }
        },
        topAppBarTitle = stringResource(R.string.recipes),
        navigationIcon = Icons.Default.ArrowBack,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = stringResource(R.string.navigate_up),
        actionContentDescription = stringResource(R.string.navigation_drawer),
        navController = navController,
        navigationClickHandler = { navController.navigateUp() },
        drawerState = drawerState,
        scope = scope,
        arrowBackClickHandler = { scope.launch { drawerState.open() } }
    )
}

@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        RecipeScreen(navController, drawerState, scope)
    }
}
