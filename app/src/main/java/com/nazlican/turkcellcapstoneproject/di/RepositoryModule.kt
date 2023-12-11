package com.nazlican.turkcellcapstoneproject.di

import com.nazlican.turkcellcapstoneproject.data.repo.CartRepository
import com.nazlican.turkcellcapstoneproject.data.repo.DetailRepository
import com.nazlican.turkcellcapstoneproject.data.repo.HomeRepository
import com.nazlican.turkcellcapstoneproject.data.source.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(productService: ProductService) = HomeRepository(productService)

    @Provides
    @Singleton
    fun provideDetailRepository(productService: ProductService) = DetailRepository(productService)

    @Provides
    @Singleton
    fun provideCartRepository(productService: ProductService) = CartRepository(productService)

}