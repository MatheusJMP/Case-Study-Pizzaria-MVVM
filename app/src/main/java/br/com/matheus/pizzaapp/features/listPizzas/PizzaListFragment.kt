package br.com.matheus.pizzaapp.features.listPizzas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.matheus.pizzaapp.R
import br.com.matheus.pizzaapp.databinding.FragmentPizzaListBinding
import br.com.matheus.pizzaapp.helper.hide
import br.com.matheus.pizzaapp.helper.hideKeyboard
import br.com.matheus.pizzaapp.helper.show
import br.com.matheus.pizzaapp.helper.userStopDigit
import br.com.matheus.pizzaapp.model.Pizza

class PizzaListFragment : Fragment() {

    private lateinit var viewModel: PizzaViewModel
    private var binding: FragmentPizzaListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pizza_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createBinding = FragmentPizzaListBinding.bind(view)
        binding = createBinding

        viewModel = ViewModelProvider(requireActivity()).get(PizzaViewModel::class.java)
        if (viewModel.listPizzas.isNotEmpty()) {
            setupAdapter(viewModel.listPizzas)
        } else {
            viewModel.pizzaList()
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.pizzaListState.observe(viewLifecycleOwner, {
            setupAdapter(it)
        })
        viewModel.loadingState.observe(viewLifecycleOwner, {
            if (it) {
                binding?.loadingListPizzas?.show()
                binding?.recyclerViewPizza?.hide()
            } else {
                binding?.loadingListPizzas?.hide()
                binding?.recyclerViewPizza?.show()
            }
        })
    }

    private fun setupAdapter(it: List<Pizza>) {
        viewModel.listPizzas = it
        val pizzaAdapter = PizzaAdapter()
        pizzaAdapter.setListPizza(it)
        binding?.recyclerViewPizza?.adapter = pizzaAdapter
        binding?.searchView?.apply {
            userStopDigit { pizzaName ->
                pizzaAdapter.setFilter(pizzaName)
                if (pizzaName.isEmpty()) {
                    setupAdapter(viewModel.listPizzas)
                    hideKeyboard()
                    this.clearFocus()
                }
            }
        }
        pizzaAdapter.onPizzaClick = { pizza ->
            viewModel.selectedPizza = pizza
            findNavController().navigate(R.id.action_pizzaList_to_pizzaDetail)
        }
    }
}