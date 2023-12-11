package com.nazlican.turkcellcapstoneproject.data.repo

import com.nazlican.turkcellcapstoneproject.common.Resource
import com.nazlican.turkcellcapstoneproject.data.model.Products
import com.nazlican.turkcellcapstoneproject.data.source.remote.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DetailRepository(private val productService: ProductService) {

    suspend fun getDetail(id: Int): Resource<Products> =
        withContext(Dispatchers.IO) {
            try {
                val response = productService.getProductDetail(id)
                val product = response.body()
                if (response.isSuccessful && product != null) {
                    Resource.Success(product)
                } else {
                    Resource.Fail("Bir hata oluştu")
                }
            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }
}
