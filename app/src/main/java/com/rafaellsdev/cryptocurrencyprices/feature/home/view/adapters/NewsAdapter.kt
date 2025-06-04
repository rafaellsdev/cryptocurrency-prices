package com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaellsdev.cryptocurrencyprices.commons.model.NewsArticle
import com.rafaellsdev.cryptocurrencyprices.databinding.NewsItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(
    private var news: List<NewsArticle>
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    fun updateNews(news: List<NewsArticle>) {
        this.news = news
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsArticle) {
            binding.txtNewsTitle.text = item.title
            binding.txtNewsSource.text = item.source
            Picasso.get().load(item.imageUrl).into(binding.imgNewsThumbnail)
        }
    }
}
