package com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaellsdev.cryptocurrencyprices.databinding.GlobalMetricsItemBinding

class GlobalMetricsAdapter(
    private var items: List<Pair<String, String>>
) : RecyclerView.Adapter<GlobalMetricsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GlobalMetricsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(items: List<Pair<String, String>>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: GlobalMetricsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pair<String, String>) {
            binding.txtMetricTitle.text = item.first
            binding.txtMetricValue.text = item.second
        }
    }
}
