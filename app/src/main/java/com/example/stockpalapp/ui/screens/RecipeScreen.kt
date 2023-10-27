package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.components.FullRecipeCard
import com.example.stockpalapp.ui.components.SearchComponent
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.RecipeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RecipeList(){
    val recipeScreenViewModel: RecipeScreenViewModel = hiltViewModel()
    val recipeList by recipeScreenViewModel.recipes.collectAsState(initial = emptyList())

    LazyColumn{
        items(recipeList){ item ->
            FullRecipeCard(
            title = item.title,
            imageUrl = item.image,
            cuisine = item.cuisine,
            cookingTime = item.cookingTime,
            ingredients = item.ingredients,
            instructions = item.instructions
        )
            {
                //no content yet
            }
        }
    }
}
@Composable
fun RecipePage(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SearchComponent()
        Text(text = stringResource(R.string.suggestions), style = TextStyle(fontSize = 20.sp))
        RecipeList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){
    AppLayout(content = { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            RecipePage()
        }},
        topAppBarTitle = stringResource(R.string.recipes),
        navigationIcon = Icons.Default.ArrowBack,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = stringResource(R.string.navigate_up),
        actionContentDescription = stringResource(R.string.navigation_drawer),
        navController = navController,
        navigationClickHandler = {navController.navigateUp()},
        drawerState = drawerState,
        scope = scope,
        arrowBackClickHandler = {scope.launch { drawerState.open() }}
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        RecipeScreen(navController, drawerState, scope)

    }
}

