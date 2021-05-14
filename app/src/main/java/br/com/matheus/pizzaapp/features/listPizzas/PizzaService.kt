package br.com.matheus.pizzaapp.features.listPizzas

import br.com.matheus.pizzaapp.data.CustomResponse
import br.com.matheus.pizzaapp.model.Pizza

interface PizzaService {
    suspend fun getPizzas() : CustomResponse<List<Pizza>>
}