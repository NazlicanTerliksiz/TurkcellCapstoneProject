package com.nazlican.turkcellcapstoneproject.data.repo

import com.nazlican.turkcellcapstoneproject.common.Resource
import com.nazlican.turkcellcapstoneproject.data.model.AddToCart
import com.nazlican.turkcellcapstoneproject.data.model.Cart
import com.nazlican.turkcellcapstoneproject.data.source.remote.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CartRepository (private val productService: ProductService) {

    suspend fun getCartProducts() : Resource<List<Cart>> =
        withContext(Dispatchers.IO){
            try {
                val response = productService.getCartProducts()
                val cartList = response.body()?.carts
                if (response.isSuccessful && cartList != null){
                    Resource.Success(cartList)
                }else
                    Resource.Fail("An error occurred")
            }catch (e:Exception){
                Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun addToCart(addToCart: AddToCart) : Resource<Cart> =
        withContext(Dispatchers.IO){
            try {
                val response = productService.addToCart(addToCart)
                val addProduct = response.body()
                if (response.isSuccessful && addProduct != null){
                    Resource.Success(addProduct)
                }else
                    Resource.Fail("An error occurred")
            }catch (e:Exception){
                Resource.Error(e.message.orEmpty())
            }
        }
}