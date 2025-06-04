package com.rafaellsdev.cryptocurrencyprices.feature.home.view.components

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.commons.ext.onClick
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.databinding.CurrencyDetailsBottomSheetBinding
import java.text.NumberFormat
import java.util.Currency.getInstance

class CurrencyDetailsBottomSheet private constructor() {

    companion object {

        fun createDialog(
            context: Context,
            dismissAction: () -> Unit,
            fullExpand: Boolean = false,
            currency: Currency? = null,
            currencyCode: String
        ): BottomSheetDialog {

            val contentView = CurrencyDetailsBottomSheetView(context).apply {
                configure(dismissAction, fullExpand, currency, currencyCode)
            }

            val dialog = BottomSheetDialog(context)
            dialog.setContentView(contentView)

            val dialogFrame: FrameLayout? =
                dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)

            dialogFrame?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                it.setBackgroundColor(Color.TRANSPARENT)
            }

            return dialog
        }
    }
}

private class CurrencyDetailsBottomSheetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        CurrencyDetailsBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun configure(
        dismissAction: () -> Unit,
        fullExpand: Boolean = false,
        currency: Currency?,
        currencyCode: String
    ) {
        binding.imgClose.onClick(dismissAction)
        binding.bottomSheetCurrencyName.text = currency?.name ?: ""
        binding.bottomSheetPriceValue.text = formatPrice(currency?.currentPrice, currencyCode)
        val change = currency?.priceChangePercentage ?: 0.0
        val priceColor = if (change > 0) {
            context.getColor(R.color.positive_green)
        } else {
            context.getColor(R.color.orange_highlight)
        }
        binding.bottomSheetPriceValue.setTextColor(priceColor)
        binding.bottomSheetHighestPriceValue.text = formatPrice(currency?.highPrice, currencyCode)
        binding.bottomSheetLowestPriceValue.text = formatPrice(currency?.lowPrice, currencyCode)
        binding.bottomSheetVolumeValue.text = formatPrice(currency?.totalVolume, currencyCode)
        binding.bottomSheetCirculatingSupplyValue.text = formatNumber(currency?.circulatingSupply)
        binding.bottomSheetTotalSupplyValue.text = formatNumber(currency?.totalSupply)

        setHeight(fullExpand)
    }

    private fun formatPrice(price: Any?, currencyCode: String): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = getInstance(currencyCode)
        return format.format(price)
    }

    private fun formatNumber(value: Any?): String {
        val format: NumberFormat = NumberFormat.getNumberInstance()
        format.maximumFractionDigits = 0
        return format.format(value)
    }


    private fun setHeight(isFullExpand: Boolean) {
        if (isFullExpand) {
            binding.cstContent.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            binding.cstContent.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
}