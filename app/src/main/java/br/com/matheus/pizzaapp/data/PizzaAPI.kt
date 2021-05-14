package br.com.matheus.pizzaapp.data

import br.com.matheus.pizzaapp.model.Pizza
import br.com.matheus.pizzaapp.model.UserLogin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PizzaAPI {

    @GET("/v1/pizza")
    suspend fun getPizzas(): List<Pizza>

    @POST("/v1/signin")
    suspend fun postLogin(
        @Body credentials: UserLogin
    )

}