package br.com.matheus.pizzaapp.features.login

import br.com.matheus.pizzaapp.data.CustomResponse
import br.com.matheus.pizzaapp.data.PizzaAPI
import br.com.matheus.pizzaapp.model.UserLogin

class LoginRepository(private val api: PizzaAPI) : LoginService {

    override suspend fun login(login: UserLogin): CustomResponse<Unit> {
        return try {
            val response = api.postLogin(login)
            CustomResponse.Success(response)
        } catch (e: Exception) {
            CustomResponse.Error(e)
        }
    }
}
