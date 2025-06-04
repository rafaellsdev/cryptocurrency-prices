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
    val clickListener: (Currency) -> Unit
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

    // getItemCount() is no longer needed as ListAdapter handles it internally.

    fun updateCurrencies(currencies: List<Currency>) {
        submitList(currencies)
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
        }
    }

    private fun formatPrice(price: Any?): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = java.util.Currency.getInstance("EUR")
        return format.format(price)
    }
}