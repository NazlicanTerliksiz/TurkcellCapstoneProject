package com.nazlican.turkcellcapstoneproject.di

import com.nazlican.turkcellcapstoneproject.data.source.remote.ProductService
import com.nazlican.turkcellcapstoneproject.util.constants.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    val okHttpClient = OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideSimpsonsQuoteService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}