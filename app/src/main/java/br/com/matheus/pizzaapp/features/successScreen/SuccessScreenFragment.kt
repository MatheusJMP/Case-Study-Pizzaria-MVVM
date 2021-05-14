package br.com.matheus.pizzaapp.features.successScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.matheus.pizzaapp.R
import br.com.matheus.pizzaapp.databinding.FragmentSuccessScreenBinding

class SuccessScreenFragment : Fragment() {

    private var binding: FragmentSuccessScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_success_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createBinding = FragmentSuccessScreenBinding.bind(view)
        binding = createBinding

        binding?.buttonBack?.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_successScreen_to_pizzaList)
            }
        }
    }
}