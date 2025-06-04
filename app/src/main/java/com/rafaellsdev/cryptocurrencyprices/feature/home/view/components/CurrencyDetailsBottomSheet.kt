package com.rafaellsdev.cryptocurrencyprices.feature.home.view.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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
            showChartAction: () -> Unit,
            fullExpand: Boolean = false,
            currency: Currency? = null
        ): BottomSheetDialog {

            val contentView = CurrencyDetailsBottomSheetView(context).apply {
                configure(dismissAction, showChartAction, fullExpand, currency)
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

    fun configure(
        dismissAction: () -> Unit,
        showChartAction: () -> Unit,
        fullExpand: Boolean = false,
        currency: Currency?
    ) {
        binding.imgClose.onClick(dismissAction)
        binding.btnShowChart.onClick(showChartAction)
        binding.bottomSheetCurrencyName.text = currency?.name ?: ""
        binding.bottomSheetPriceValue.text = formatPrice(currency?.currentPrice)
        binding.bottomSheetHighestPriceValue.text = formatPrice(currency?.highPrice)
        binding.bottomSheetLowestPriceValue.text = formatPrice(currency?.lowPrice)

        setHeight(fullExpand)
    }

    private fun formatPrice(price: Any?): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = getInstance("EUR")
        return format.format(price)
    }


    private fun setHeight(isFullExpand: Boolean) {
        if (isFullExpand) {
            binding.cstContent.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            binding.cstContent.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
}
