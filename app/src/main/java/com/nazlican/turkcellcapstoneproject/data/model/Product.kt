package com.nazlican.turkcellcapstoneproject.data.model

data class Product(
    val discountPercentage: Double,
    val discountedPrice: Int,
    val id: Int,
    val price: Int,
    val quantity: Int,
    val thumbnail: String,
    val title: String,
    val total: Int
)