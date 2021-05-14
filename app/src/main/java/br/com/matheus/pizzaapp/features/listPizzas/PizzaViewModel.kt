package br.com.matheus.pizzaapp.features.listPizzas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.matheus.pizzaapp.data.CustomResponse
import br.com.matheus.pizzaapp.model.Pizza
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class PizzaViewModel(private val pizzaRepository: PizzaService) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val loadingElement = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = loadingElement

    private val pizzaListElement = MutableLiveData<List<Pizza>>()
    val pizzaListState: LiveData<List<Pizza>> get() = pizzaListElement

    var selectedPizza : Pizza? = null
    var listPizzas : List<Pizza> = arrayListOf()

    fun pizzaList() {
        loadingElement.value = true
        launch {
            val result = withContext(Dispatchers.IO) {
                pizzaRepository.getPizzas()
            }
            when (result) {
                is CustomResponse.Success -> {
                    loadingElement.value = false
                    pizzaListElement.value = result.data
                }
                is CustomResponse.Error -> {
                    loadingElement.value = false
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}