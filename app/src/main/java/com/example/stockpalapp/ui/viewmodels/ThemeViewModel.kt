package com.example.stockpalapp.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel(){

    private val _useDarkTheme = mutableStateOf(false)
    val useDarkTheme: State<Boolean> = _useDarkTheme

    fun setDarkTheme(isDark: Boolean) {
        _useDarkTheme.value = isDark
    }

}