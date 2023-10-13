package com.example.stockpalapp.data.repositories.module

import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.impl.PantryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun providePantryRepository(impl: PantryRepositoryImpl): PantryRepository
}