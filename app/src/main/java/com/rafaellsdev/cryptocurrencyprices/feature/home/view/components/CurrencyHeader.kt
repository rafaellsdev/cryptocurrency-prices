package com.rafaellsdev.cryptocurrencyprices.feature.home.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.databinding.CurrencyHeaderBinding

class CurrencyHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CurrencyHeaderBinding = CurrencyHeaderBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private var title: String = ""
        set(value) {
            field = value
            setHeaderTitle(value)
        }

    init {
        attrs?.let {
            handleAttr(context, it)
        }
    }

    private fun setHeaderTitle(text: String) {
        binding.headerTitle.text = text
    }

    private fun handleAttr(context: Context, attrs: AttributeSet) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.CurrencyHeader)
        title = array.getString(R.styleable.CurrencyHeader_currency_header_title) ?: ""
        array.recycle()
    }

    fun changeTitle(newTitle: String) {
        title = newTitle
    }

    fun getBinding(): ViewBinding {
        return binding
    }
}