package com.nazlican.turkcellcapstoneproject.data.repo

import com.nazlican.turkcellcapstoneproject.common.Resource
import com.nazlican.turkcellcapstoneproject.data.model.Products
import com.nazlican.turkcellcapstoneproject.data.source.remote.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeRepository(private val productService: ProductService) {

    suspend fun getAllProducts(): Resource<List<Products>> =
        withContext(Dispatchers.IO) {
            try {
                val response = productService.getProducts()
                if (response.isSuccessful) {
                    val productList = response.body()?.products.orEmpty()
                    Resource.Success(productList)
                }else{
                    Resource.Fail("An error occurred")
                }
            }catch (e:Exception){
                Resource.Error(e.message.orEmpty())
            }
        }
}