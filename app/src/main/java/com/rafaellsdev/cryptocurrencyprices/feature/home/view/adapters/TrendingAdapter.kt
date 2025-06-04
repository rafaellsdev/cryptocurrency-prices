package com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin
import com.rafaellsdev.cryptocurrencyprices.databinding.TrendingCurrencyItemBinding
import com.squareup.picasso.Picasso

class TrendingAdapter(
    private var trendingCoins: List<TrendingCoin>
) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TrendingCurrencyItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = trendingCoins.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trendingCoins[position])
    }

    fun updateTrending(coins: List<TrendingCoin>) {
        trendingCoins = coins
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: TrendingCurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: TrendingCoin) {
            binding.txtTrendingSymbol.text = coin.symbol.uppercase()
            Picasso.get().load(coin.image).into(binding.imgTrendingIcon)
        }
    }
}
