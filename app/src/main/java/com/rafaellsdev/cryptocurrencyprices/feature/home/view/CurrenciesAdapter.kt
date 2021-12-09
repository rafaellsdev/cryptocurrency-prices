package com.rafaellsdev.cryptocurrencyprices.feature.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.databinding.CryptoCurrencyItemBinding

class CurrenciesAdapter(
    private val currenciesList: List<Currency>,
    val clickListener: (Currency, Int) -> Unit
) :
    RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    var onItemClick: ((Currency) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding =
            CryptoCurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = currenciesList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = currenciesList.size

    inner class ViewHolder(private val itemBinding: CryptoCurrencyItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(currency: Currency) {
            itemBinding.txtCurrencyName.text = currency.name
        }

    }
}