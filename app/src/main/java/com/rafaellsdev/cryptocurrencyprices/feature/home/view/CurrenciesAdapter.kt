package com.rafaellsdev.cryptocurrencyprices.feature.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency

class CurrenciesAdapter(
    private val driversList: List<Currency>,
    val clickListener: (Currency, Int) -> Unit
) :
    RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    var onItemClick: ((Currency) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.crypto_currency_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = driversList[position]
        holder.name.text = currentItem.name
        holder.itemView.setOnClickListener { clickListener(currentItem, position) }
    }

    override fun getItemCount(): Int = driversList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txt_currency_name)
    }
}