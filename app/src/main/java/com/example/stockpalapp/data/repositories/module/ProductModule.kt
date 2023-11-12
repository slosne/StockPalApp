package com.example.stockpalapp.data.repositories.module

import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.ProductRepository
import com.example.stockpalapp.data.repositories.impl.PantryRepositoryImpl
import com.example.stockpalapp.data.repositories.impl.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductModule {
    @Binds
    abstract fun provideProductRepository(impl: ProductRepositoryImpl): ProductRepository
}