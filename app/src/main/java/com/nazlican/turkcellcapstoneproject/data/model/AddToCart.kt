package com.nazlican.turkcellcapstoneproject.data.model

data class AddToCart(
    val id: Long,
    val products: List<Product>,
    val total: Long,
    val discountedTotal: Long,
    val userID: Long,
    val totalProducts: Long,
    val totalQuantity: Long
)