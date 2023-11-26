package com.example.stockpalapp.data.module


import com.example.stockpalapp.data.repositories.RecipeRepository
import com.example.stockpalapp.data.repositories.impl.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RecipeModule {
    @Binds
    abstract fun provideRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository
}