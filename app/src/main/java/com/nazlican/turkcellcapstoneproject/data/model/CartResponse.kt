package com.nazlican.turkcellcapstoneproject.data.model

data class CartResponse(
    val carts: List<Cart>,
    val limit: Int,
    val skip: Int,
    val total: Int
)