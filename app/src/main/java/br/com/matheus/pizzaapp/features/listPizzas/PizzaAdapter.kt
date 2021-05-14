package br.com.matheus.pizzaapp.features.listPizzas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.matheus.pizzaapp.R
import br.com.matheus.pizzaapp.helper.setPizzaImage
import br.com.matheus.pizzaapp.helper.toMonetary
import br.com.matheus.pizzaapp.model.Pizza
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pizza_list.view.*

class PizzaAdapter : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    private var listPizza = arrayListOf<Pizza>()
    lateinit var onPizzaClick: (Pizza) -> Unit
    fun setListPizza(list: List<Pizza>) {
        listPizza = ArrayList(list)
        notifyDataSetChanged()
    }

    fun setFilter(filter: String) {
        val filteredList = listPizza.filter { pizza ->
            pizza.name.contains(filter) || pizza.name.toLowerCase()
                .contains(filter) || pizza.name.toUpperCase().contains(filter)
        }
        setListPizza(filteredList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pizza_list, parent, false)

        return PizzaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.bindPizza(listPizza[position])
    }

    override fun getItemCount(): Int = listPizza.size

    inner class PizzaViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bindPizza(pizza: Pizza) {
            with(view) {
                cardViewPizzaClick.setOnClickListener {
                    if (::onPizzaClick.isInitialized)
                        onPizzaClick.invoke(pizza)
                }
                imageViewPizza.setPizzaImage(pizza.imageUrl)
                textViewPizzaName.text = pizza.name
                textViewPizzaPrice.text = pizza.priceM.toMonetary()
                ratingBarPizza.rating = pizza.rating.toFloat()
            }
        }
    }
}