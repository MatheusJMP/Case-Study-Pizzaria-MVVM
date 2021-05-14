package br.com.matheus.pizzaapp.features.navigationPizzas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.matheus.pizzaapp.R
import br.com.matheus.pizzaapp.features.listPizzas.PizzaViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    companion object {
        fun Activity.openNavigationActivity() {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
    }
    private lateinit var viewModel: PizzaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val injectViewModel: PizzaViewModel by viewModel()
        viewModel = injectViewModel
    }
}