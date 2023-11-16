package com.example.stockpalapp.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.components.FilledBtn
import com.example.stockpalapp.ui.components.ProductListItem
import com.example.stockpalapp.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.stockpalapp.ui.components.SmallRecipeCard
import com.example.stockpalapp.ui.model.Routes

@Composable
fun WelcomeSection(name: String){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Divider()
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Velkommen, " + name + "!",
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(10.dp))
    }
}

@Composable
fun ExpDateSection(navController: NavController) {
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    val sortedList by homeScreenViewModel.sortedProductsByExpDate.
    collectAsState(initial = emptyList())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Divider()
        Spacer(modifier = Modifier.size(10.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth())
        {
            Text(text = stringResource(R.string.closest_exp_date),
                style = MaterialTheme.typography.titleLarge)
            FilledBtn(
                clickHandler = { navController.navigate(Routes().pantry) },
                btnText = stringResource(R.string.to_all_items))
        }
        Spacer(modifier = Modifier.size(5.dp))
        LazyColumn() {
            items(sortedList.take(2)){item ->
                ProductListItem(
                    title = item.name,
                    description = null,
                    ammount = null,
                    imageUrl = item.image,
                    date = item.expDate
                ) {

                }
            }
        }
        Divider()
    }
}

@Composable
fun RecommendedRecipeCard(navController: NavController){
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val recipeList by homeScreenViewModel.recipes.collectAsState(initial = emptyList())

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp))
    {
        item {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth())
            {
                FilledBtn(
                    clickHandler = { navController.navigate(Routes().recipes) }, 
                    btnText = "Alle oppskrifter")
                Text(text = stringResource(R.string.recommodation),
                    style = MaterialTheme.typography.titleLarge)
            }
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
    scope: CoroutineScope)
{

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    AppLayout(
        content = { paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)) {
                Spacer(modifier = Modifier.size(20.dp))
                WelcomeSection(homeScreenViewModel.currentUser.toString())
                Spacer(modifier = Modifier.size(10.dp))
                ExpDateSection(navController)
                Spacer(modifier = Modifier.size(10.dp))
                RecommendedRecipeCard(navController)
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

/*
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


 */
