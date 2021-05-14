package br.com.matheus.pizzaapp.model

data class UserLogin(
    val email: String,
    val password: String
)

fun createUserLogin(email: String, password: String): UserLogin {
    return UserLogin(email, password)
}
