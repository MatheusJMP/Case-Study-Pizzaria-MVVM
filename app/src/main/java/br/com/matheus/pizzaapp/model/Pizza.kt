package br.com.matheus.pizzaapp.model

data class Pizza(
    val id: String,
    val imageUrl: String,
    val name: String,
    val priceG: Double,
    val priceM: Double,
    val priceP: Double,
    val rating: Int
)