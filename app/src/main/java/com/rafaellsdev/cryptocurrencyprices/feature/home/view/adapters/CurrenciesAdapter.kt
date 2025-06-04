package com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.databinding.CryptoCurrencyItemBinding
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class CurrenciesAdapter(
    private var currenciesList: List<Currency>,
    val clickListener: (Currency) -> Unit,
    private val isFavorite: (String) -> Boolean,
    private val toggleFavorite: (String) -> Unit
) :
    RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding =
            CryptoCurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = currenciesList[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { clickListener(currentItem) }
    }

    override fun getItemCount(): Int = currenciesList.size

    fun updateCurrencies(currencies: List<Currency>) {
        currenciesList = currencies
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: CryptoCurrencyItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(currency: Currency) {
            itemBinding.txtCurrencyName.text = currency.name
            itemBinding.txtCurrencyPrice.text = formatPrice(currency.currentPrice)
            itemBinding.txtCurrencyPriceChangePercentage.text =
                String.format("%.2f", currency.priceChangePercentage) + "%"
            itemBinding.imgCurrencyIcon
            Picasso.get().load(currency.image).into(itemBinding.imgCurrencyIcon)

            val favoriteRes = if (isFavorite(currency.id)) {
                com.rafaellsdev.cryptocurrencyprices.R.drawable.ic_star
            } else {
                com.rafaellsdev.cryptocurrencyprices.R.drawable.ic_star_border
            }
            itemBinding.imgFavorite.setImageResource(favoriteRes)
            itemBinding.imgFavorite.setOnClickListener {
                toggleFavorite(currency.id)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    private fun formatPrice(price: Any?): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = java.util.Currency.getInstance("EUR")
        return format.format(price)
    }
}