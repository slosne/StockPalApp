package com.example.stockpalapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpalapp.ui.model.BottomNavItem
import com.example.stockpalapp.ui.model.DrawerItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun BottomNavigation(navController: NavController){

    val currentRoute = navController.currentBackStackEntry?.destination?.route

    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Shoppinglist,
        BottomNavItem.AddPantryItem,
        BottomNavItem.Pantry,
        BottomNavItem.Recipe
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.DarkGray)
    {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            navItems.forEach { item ->
                val selected = currentRoute == item.screen_route
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Blue,
                        selectedIconColor = Color.White
                    ),
                    selected = selected,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {saveState = true}
                            }
                            launchSingleTop = true
                            restoreState = true}
                    },
                    icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                    label = { Text(item.title) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HamburgerMenuContent(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){

    val drawerItems = listOf(
        DrawerItem.Home,
        DrawerItem.Pantry,
        DrawerItem.Shoppinglist,
        DrawerItem.Recipe,
        DrawerItem.Profile
    )

    val currentRoute = navController.currentBackStackEntry?.destination?.route


    ModalDrawerSheet {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 64.dp),
            contentAlignment = Alignment.Center
        )
        {
            Text(text = stringResource(R.string.stockpal), style = MaterialTheme.typography.headlineSmall)
        }

        drawerItems.forEach { item ->
            val selected = currentRoute == item.screen_route
            NavigationDrawerItem(
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.tertiary,
                    selectedIconColor = MaterialTheme.colorScheme.onTertiary,
                    selectedTextColor = MaterialTheme.colorScheme.onTertiary
                ),
                selected = selected,
                label = { Text(text = item.label, style = MaterialTheme.typography.labelLarge) },
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {saveState = true}
                        }
                        launchSingleTop = true
                        restoreState = true}
                    scope.launch { drawerState.close() }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(
    content: @Composable (PaddingValues) -> Unit,
    navigationClickHandler: () -> Unit = {},
    arrowBackClickHandler: () -> Unit = {},
    topAppBarTitle: String,
    navigationIcon: ImageVector?,
    actionIcon: ImageVector,
    navigationContentDescription: String?,
    actionContentDescription: String?,
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HamburgerMenuContent(navController, drawerState, scope)
        }

    ){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = topAppBarTitle,
                        style = MaterialTheme.typography.headlineMedium) },
                    navigationIcon = {
                        IconButton(onClick = {navigationClickHandler()}) {
                            if(navigationIcon != null){
                                Icon(
                                    imageVector = navigationIcon,
                                    contentDescription = navigationContentDescription
                                )
                            }
                        }
                    }, actions = {
                        IconButton(onClick = {arrowBackClickHandler()}) {
                                Icon(
                                    imageVector = actionIcon,
                                    contentDescription = actionContentDescription
                                )
                        }
                    }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
                )
            },
        bottomBar = {
                BottomNavigation(navController)

        },
         content = { paddingValues ->
            content(paddingValues)
        },
    )

}}