package br.com.matheus.pizzaapp.features.pizzaDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import br.com.matheus.pizzaapp.R
import br.com.matheus.pizzaapp.databinding.FragmentPizzaDetailBinding
import br.com.matheus.pizzaapp.features.listPizzas.PizzaViewModel
import br.com.matheus.pizzaapp.helper.setPizzaImage
import br.com.matheus.pizzaapp.helper.toMonetary

class PizzaDetailFragment : Fragment() {

    private lateinit var viewModel: PizzaViewModel
    private var binding: FragmentPizzaDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pizza_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createBinding = FragmentPizzaDetailBinding.bind(view)
        binding = createBinding

        viewModel = ViewModelProvider(requireActivity()).get(PizzaViewModel::class.java)

        setupListeners()
        viewModel.selectedPizza?.apply {
            binding?.imageViewPizzaDetail?.setPizzaImage(this.imageUrl)
            binding?.textViewPizzaNameDetail?.text = this.name
            binding?.ratingBarPizzaDetail?.rating = this.rating.toFloat()
            binding?.buttonSizeM?.performClick()
        }
    }

    private fun setupListeners() {
        binding?.imageViewBack?.apply {
            setOnClickListener {
                activity?.onBackPressed()
            }
        }

        binding?.viewButton?.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_pizzaDetail_to_successScreen)
            }
        }

        binding?.buttonSizeG?.apply {
            setOnClickListener {
                binding?.textViewPizzaValueDetail?.text =
                    viewModel.selectedPizza?.priceG?.toMonetary()
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_button_corners, null)
                setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))

                binding?.buttonSizeM?.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_button_corners_white,
                        null
                    )
                    setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
                }
                binding?.buttonSizeP?.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_button_corners_white,
                        null
                    )
                    setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
                }
            }
        }

        binding?.buttonSizeM?.apply {
            setOnClickListener {
                binding?.textViewPizzaValueDetail?.text =
                    viewModel.selectedPizza?.priceM?.toMonetary()
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_button_corners, null)
                setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))

                binding?.buttonSizeG?.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_button_corners_white,
                        null
                    )
                    setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
                }
                binding?.buttonSizeP?.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_button_corners_white,
                        null
                    )
                    setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
                }
            }
        }

        binding?.buttonSizeP?.apply {
            setOnClickListener {
                binding?.textViewPizzaValueDetail?.text =
                    viewModel.selectedPizza?.priceP?.toMonetary()
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_button_corners, null)
                setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))

                binding?.buttonSizeM?.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_button_corners_white,
                        null
                    )
                    setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
                }
                binding?.buttonSizeG?.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_button_corners_white,
                        null
                    )
                    setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
                }
            }
        }
    }
}