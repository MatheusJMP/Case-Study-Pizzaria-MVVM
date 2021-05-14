package br.com.matheus.pizzaapp.helper

import java.text.NumberFormat
import java.util.*

fun Double.toMonetary(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.currency = Currency.getInstance(Locale("pt", "BR"))
    return format.format(this).replace("BRL", "R$")
}