package br.com.matheus.pizzaapp.application

import br.com.matheus.pizzaapp.BuildConfig
import br.com.matheus.pizzaapp.data.PizzaAPI
import br.com.matheus.pizzaapp.data.RetrofitClient
import br.com.matheus.pizzaapp.features.listPizzas.PizzaRepository
import br.com.matheus.pizzaapp.features.listPizzas.PizzaService
import br.com.matheus.pizzaapp.features.listPizzas.PizzaViewModel
import br.com.matheus.pizzaapp.features.login.LoginRepository
import br.com.matheus.pizzaapp.features.login.LoginService
import br.com.matheus.pizzaapp.features.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        RetrofitClient().createWebService<PizzaAPI>(
            okHttpClient = RetrofitClient().createHttpClient(),
            baseUrl = BuildConfig.BASE_PIZZA_URL
        )
    }

    viewModel { LoginViewModel(loginRepository = get()) }
    viewModel { PizzaViewModel(pizzaRepository = get()) }
    factory<LoginService> { LoginRepository(api = get()) }
    factory<PizzaService> { PizzaRepository(api = get()) }
}