package com.nazlican.turkcellcapstoneproject.data.source.remote

import com.nazlican.turkcellcapstoneproject.data.model.AddToCart
import com.nazlican.turkcellcapstoneproject.data.model.Cart
import com.nazlican.turkcellcapstoneproject.data.model.CartResponse
import com.nazlican.turkcellcapstoneproject.data.model.ProductResponse
import com.nazlican.turkcellcapstoneproject.data.model.Products
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun  getProducts() : Response<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int) : Response<Products>

    @GET("https://dummyjson.com/products")
    suspend fun searchProduct(
        @Query("query") query: String
    ): Response<ProductResponse>

    @GET("https://dummyjson.com/carts")
    suspend fun getCartProducts() : Response<CartResponse>

    @POST("add_to_cart.php")
    suspend fun addToCart(
        @Body addToCart: AddToCart
    ): Response<Cart>



}