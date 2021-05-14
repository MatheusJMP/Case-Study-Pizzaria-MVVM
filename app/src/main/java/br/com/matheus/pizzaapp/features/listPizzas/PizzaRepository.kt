package br.com.matheus.pizzaapp.features.listPizzas

import br.com.matheus.pizzaapp.data.CustomResponse
import br.com.matheus.pizzaapp.data.PizzaAPI
import br.com.matheus.pizzaapp.model.Pizza

class PizzaRepository(private val api: PizzaAPI) : PizzaService {
    override suspend fun getPizzas(): CustomResponse<List<Pizza>> {
        return try {
            val response = api.getPizzas()
            CustomResponse.Success(response)
        } catch (e: Exception) {
            CustomResponse.Error(e)
        }
    }
}