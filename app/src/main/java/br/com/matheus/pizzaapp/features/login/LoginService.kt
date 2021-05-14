package br.com.matheus.pizzaapp.features.login

import br.com.matheus.pizzaapp.data.CustomResponse
import br.com.matheus.pizzaapp.model.UserLogin

interface LoginService {
    suspend fun login(login: UserLogin) : CustomResponse<Unit>
}