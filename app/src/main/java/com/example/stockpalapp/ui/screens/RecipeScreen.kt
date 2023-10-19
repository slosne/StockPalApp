package com.example.stockpalapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.model.Models
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearch(){
    Row {
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.search))
        }
    }
}

@Composable
fun RecipeItem(models: Models, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row {
            Text(text = LocalContext.current.getString(models.stringResourceId),
                modifier = Modifier.padding(5.dp)
                    .align(CenterVertically),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.padding(122.dp))
            Image(
                painter = painterResource(models.imageResourceId),
                contentDescription = stringResource(models.stringResourceId),
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
@Composable
fun RecipeList(modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier
        .padding(vertical = 10.dp)
    ){
        item { Text(text = stringResource(R.string.missing_items),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleLarge
        ) }
        items(3){ recipeItem -> RecipeItem(Models(R.string.carrots, R.drawable.carrots)) }
    }
}
@Composable
fun RecipePage(){
    Column {
        RecipeSearch()
        Text(text = stringResource(R.string.suggestions),
            modifier = Modifier.align(CenterHorizontally))
        RecipeList()
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
        leftIconClickHandler = {navController.navigateUp()},
        drawerState = drawerState,
        scope = scope,
        rightIconClickHandler = {scope.launch { drawerState.open() }}
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightModePreview"
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkModePreview"
)

@Composable
fun RecipeScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        RecipeScreen(navController, drawerState, scope)

    }
}