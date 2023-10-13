package com.example.stockpalapp.data

import com.example.stockpalapp.R
import com.example.stockpalapp.ui.model.Models

class Datasource() {
    fun loadFoodItems(): List<Models> {
        return listOf<Models> (
            Models(R.string.carrots, R.drawable.carrots),
            Models(R.string.cheese, R.drawable.cheese),
            Models(R.string.ketchup, R.drawable.ketchup),
            Models(R.string.sausages, R.drawable.sausages)

        )
    }
    fun loadShoppingItems(): List<Models> {
        return listOf<Models> (
            Models(R.string.carrots, R.drawable.carrots),
            Models(R.string.cheese, R.drawable.cheese),
            Models(R.string.ketchup, R.drawable.ketchup),
            Models(R.string.sausages, R.drawable.sausages)

        )
    }
}
