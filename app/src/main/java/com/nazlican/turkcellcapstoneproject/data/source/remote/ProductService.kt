package com.nazlican.turkcellcapstoneproject.data.source.remote

import com.nazlican.turkcellcapstoneproject.data.model.ProductResponse
import com.nazlican.turkcellcapstoneproject.data.model.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun  getProducts() : Response<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int) : Response<Products>

}