package com.example.stockpalapp.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.stockpalapp.ui.components.FilledBtn
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.stockpalapp.ui.components.SmallRecipeCard
import com.example.stockpalapp.ui.viewmodels.PantryViewModel


@Composable
fun ExpDateSection() {
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    val sortedList by homeScreenViewModel.
    sortedProductsByExpDate.
    collectAsState(initial = emptyList())

    val pantryViewModel: PantryViewModel = hiltViewModel()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Divider()
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = stringResource(R.string.closest_exp_date),
            style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(5.dp))
            LazyRow() {
                items(sortedList){item ->
                    Surface(
                        modifier = Modifier
                            .padding(10.dp)
                            .width(200.dp)
                            .height(100.dp)) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            if (item.expDate != null)
                            {
                                Text(
                                    text = pantryViewModel.convertTimestampToString(item.expDate),
                                    style = MaterialTheme.typography.titleSmall)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            FilledBtn(clickHandler = { /* TODO */ }, btnText = stringResource(R.string.to_all_items))
            Spacer(modifier = Modifier.size(20.dp))
            Divider()
        }
}

@Composable
fun RecommendedRecipeCard(){
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val recipeList by homeScreenViewModel.recipes.collectAsState(initial = emptyList())

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp))
    {
        item {
            Text(text = stringResource(R.string.recommodation),
                style = MaterialTheme.typography.titleMedium)
        }
        items(recipeList){recipe ->
            SmallRecipeCard(
                recipe.title,
                recipe.image,
                recipe.ingredients)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope){

    AppLayout(
        content = { paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)) {
                Spacer(modifier = Modifier.size(20.dp))
                ExpDateSection()
                Spacer(modifier = Modifier.size(20.dp))
                RecommendedRecipeCard()
            }
        },
        topAppBarTitle = stringResource(R.string.stockpal),
        navigationIcon = null,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = stringResource(R.string.navigation_drawer),
        actionContentDescription = null,
        navController = navController,
        arrowBackClickHandler = {scope.launch { drawerState.open() }},
        drawerState = drawerState,
        scope = scope,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        HomeScreen(navController, drawerState, scope)
    }
}

