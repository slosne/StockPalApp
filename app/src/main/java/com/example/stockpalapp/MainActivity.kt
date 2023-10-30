package com.example.stockpalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockPalAppTheme {
                AppNavigation(authViewModel)
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPalAppTheme {
        AppNavigation()
    }
}

 */




/* KILDER
*
* Khan, B. (4. september, 2022). Firebase Authentication using MVVM with Hilt and Coroutines
* https://www.simplifiedcoding.net/firebase-authentication-using-mvvm/
*
* Brambora0 for StackOverflow (10. juli, 2022).
* https://stackoverflow.com/questions/72928622/material-3-navigationbaritem-how-to-change-selected-and-unselected-icons
*
* Arif, M (4. august, 2023). Mastering Navigation Drawer in Jetpack Compose with Material Design 3 | Android Dev | Kotlin.
* https://www.youtube.com/watch?v=2pGTSiqnW90
*
* https://developer.android.com/
* */