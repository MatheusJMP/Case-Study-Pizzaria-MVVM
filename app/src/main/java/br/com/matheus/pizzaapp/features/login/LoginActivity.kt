package br.com.matheus.pizzaapp.features.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.matheus.pizzaapp.R
import br.com.matheus.pizzaapp.databinding.ActivityLoginBinding
import br.com.matheus.pizzaapp.features.navigationPizzas.NavigationActivity.Companion.openNavigationActivity
import br.com.matheus.pizzaapp.helper.hide
import br.com.matheus.pizzaapp.helper.isEmailValid
import br.com.matheus.pizzaapp.helper.show
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.buttonLogin.setOnClickListener {
            if (getEmail().isEmailValid() && getPassword().isNotEmpty()) {
                viewModel.loginRequest(getEmail(), getPassword())
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.error_try_again),
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setBackgroundTint(resources.getColor(R.color.color_red_neon))
                    setTextColor(resources.getColor(R.color.white))
                }.show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.loadingState.observe(this, {
            if (it) {
                binding.buttonLogin.hide()
                binding.loadingLogin.show()
            } else {
                binding.buttonLogin.show()
                binding.loadingLogin.hide()
            }
        })
        viewModel.loginSuccessState.observe(this, {
            if (it) {
                openNavigationActivity()
            } else {
                Snackbar.make(binding.root, getString(R.string.error_login), Snackbar.LENGTH_SHORT)
                    .apply {
                        this.setBackgroundTint(resources.getColor(R.color.color_red_neon))
                        setTextColor(resources.getColor(R.color.white))
                    }.show()
            }
        })
    }

    private fun getEmail(): String = binding.textInputLayoutUser.text.toString()

    private fun getPassword(): String = binding.textInputLayoutPassword.text.toString()
}