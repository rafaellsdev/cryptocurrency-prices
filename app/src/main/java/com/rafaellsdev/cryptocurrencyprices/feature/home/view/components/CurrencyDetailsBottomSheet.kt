package com.rafaellsdev.cryptocurrencyprices.feature.home.view.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rafaellsdev.cryptocurrencyprices.commons.ext.onClick
import com.rafaellsdev.cryptocurrencyprices.databinding.CurrencyDetailsBottomSheetBinding

class CurrencyDetailsBottomSheet private constructor() {

    companion object {

        fun createDialog(
            context: Context,
            dismissAction: () -> Unit,
            fullExpand: Boolean = false
        ): BottomSheetDialog {

            val contentView = CurrencyDetailsBottomSheetView(context).apply {
                configure(dismissAction, fullExpand)
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
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        CurrencyDetailsBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun configure(
        dismissAction: () -> Unit,
        fullExpand: Boolean = false
    ) {
        binding.imgClose.onClick(dismissAction)

        setHeight(fullExpand)
    }


    private fun setHeight(isFullExpand: Boolean) {
        if (isFullExpand) {
            binding.cstContent.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            binding.cstContent.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
}