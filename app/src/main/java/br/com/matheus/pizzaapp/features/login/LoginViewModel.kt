package br.com.matheus.pizzaapp.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.matheus.pizzaapp.data.CustomResponse
import br.com.matheus.pizzaapp.model.createUserLogin
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel(private val loginRepository: LoginService) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val loginSuccessElement = MutableLiveData<Boolean>()
    val loginSuccessState: LiveData<Boolean> get() = loginSuccessElement

    private val loadingElement = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = loadingElement

    fun loginRequest(email: String, password: String) {
        loadingElement.value = true
        val credentials = createUserLogin(email, password)
        launch {
            val result = withContext(Dispatchers.IO) {
                loginRepository.login(credentials)
            }
            when (result) {
                is CustomResponse.Success -> {
                    loadingElement.value = false
                    loginSuccessElement.value = true
                }
                is CustomResponse.Error -> {
                    loadingElement.value = false
                    loginSuccessElement.value = false
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}