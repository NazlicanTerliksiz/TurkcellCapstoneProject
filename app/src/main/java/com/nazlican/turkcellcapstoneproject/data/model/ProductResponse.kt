package com.nazlican.turkcellcapstoneproject.data.model

data class ProductResponse(
    val limit: Int,
    val products: List<Products>,
    val skip: Int,
    val total: Int
)
