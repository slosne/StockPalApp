package com.example.stockpalapp.presentation.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.presentation.components.FilledBtn
import com.example.stockpalapp.presentation.components.ProductListItem
import com.example.stockpalapp.presentation.components.SmallRecipeCard
import com.example.stockpalapp.presentation.model.Routes
import com.example.stockpalapp.presentation.theme.StockPalAppTheme
import com.example.stockpalapp.presentation.viewmodels.HomeScreenViewModel
import com.example.stockpalapp.presentation.viewmodels.PantryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun WelcomeSection(name: String) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(modifier = Modifier.size(30.dp))
        Text(text = stringResource(R.string.hello) + name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ExpDateSection(navController: NavController) {

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val pantryViewModel: PantryViewModel = hiltViewModel()

    val sortedList by homeScreenViewModel.sortedProductsByExpDate.
    collectAsState(initial = emptyList())


    Surface(tonalElevation = 2.dp,
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 10.dp,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {

        Column(modifier = Modifier
            .padding(
                start=5.dp,
                top=5.dp,
                end=5.dp,
                bottom=40.dp))
        {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(text = stringResource(R.string.closest_exp_date),
                    style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.padding(10.dp))
                FilledBtn(
                    clickHandler = { navController.navigate(Routes().pantry) },
                    btnText = stringResource(R.string.to_all_items)
                )
            }

            //Varer fra matskapet får tekst og varselikon
            //dersom det er mindre enn 3 dager til de utløper

            sortedList.take(3).map { item ->
                if(item.expDate != null){
                    val daysRemaining = homeScreenViewModel.calculateDaysRemaining(item.expDate)
                    val warningMessage = when {
                        daysRemaining in 1..2 -> stringResource(R.string.soon_expired).uppercase()
                        (daysRemaining ?: 0) <= 0 -> stringResource(R.string.expired).uppercase()
                        else -> null
                    }

                    ProductListItem(
                        title = item.name,
                        description = warningMessage,
                        amount = null,
                        imageUrl = item.image,
                        date = item.expDate,
                        actions = {
                            when {
                                daysRemaining in 1..2 -> {
                                    IconButton(onClick = { navController.navigate(Routes().pantry) }) {
                                        Icon(
                                            imageVector = Icons.Default.Notifications,
                                            contentDescription = stringResource(R.string.expiry_date_warning_icon),
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }

                                daysRemaining!! <= 0 -> {
                                    IconButton(onClick = { pantryViewModel.removePantryProduct(item.id) }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = stringResource(R.string.expired_item_delete),
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeLayout(navController: NavController) {

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val recipeList by homeScreenViewModel.recipes.collectAsState(initial = emptyList())
    val currentUser = homeScreenViewModel.currentUser.toString()

    LazyColumn(modifier = Modifier.padding(
        start = 10.dp,
        end = 10.dp))
    {
        item {
            WelcomeSection(currentUser)
            Spacer(modifier = Modifier.size(40.dp))
            ExpDateSection(navController)
            Spacer(modifier = Modifier.size(40.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                Text(text = stringResource(R.string.recommodation),
                    style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.padding(10.dp))
                FilledBtn(
                    clickHandler = { navController.navigate(Routes().recipes) },
                    btnText = stringResource(R.string.all_recipes)
                )
            }
        }
        items(recipeList){recipe ->
            SmallRecipeCard(
                recipe.title,
                recipe.image,
                recipe.ingredients,
                navController)
        }
    }
}


@Composable
fun HomeScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope)
{
    AppLayout(
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                HomeLayout(navController)
            }
        },
        topAppBarTitle = stringResource(R.string.stockpal),
        navigationIcon = null,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = stringResource(R.string.navigation_drawer),
        actionContentDescription = null,
        navController = navController,
        arrowBackClickHandler = { scope.launch { drawerState.open() } },
        drawerState = drawerState,
        scope = scope,
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    StockPalAppTheme(useDarkTheme = true) {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        HomeScreen(
            navController = navController,
            drawerState = drawerState,
            scope = scope)
    }
}
