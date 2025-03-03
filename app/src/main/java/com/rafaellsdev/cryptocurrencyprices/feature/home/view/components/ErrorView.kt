package com.rafaellsdev.cryptocurrencyprices.feature.home.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.databinding.ErrorViewBinding


class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes) {

    private val binding =
        ErrorViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var errorListener: ErrorListener? = null
    private var title = ""
    private var message = ""
    private var button = ""

    interface ErrorListener {
        fun tryAgainAction()
    }

    init {
        attrs?.let {
            applyCustomAttributes(it)
        }
    }

    fun setup(listener: ErrorListener) {
        errorListener = listener
    }

    fun updateViewMessages(
        title: String,
        message: String,
        buttonMessage: String
    ) {
        binding.txtTitleError.text = title

        binding.txtMessageError.text = message

        binding.btnTryAgainError.text = buttonMessage
    }

    private fun applyCustomAttributes(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ErrorView)

        attributes.getString(R.styleable.ErrorView_error_view_title)?.let {
            title = it
        }

        attributes.getString(R.styleable.ErrorView_error_view_message)?.let {
            message = it
        }

        attributes.getString(R.styleable.ErrorView_error_view_button_text)?.let {
            button = it
        }

        attributes.recycle()
        updateViewMessages(
            title = title,
            message = message,
            buttonMessage = button
        )

        binding.btnTryAgainError.setOnClickListener {
            tryAgain()
        }
    }

    fun tryAgain() {
        errorListener?.tryAgainAction()
    }

    fun setMessage(message: String) {
        binding.txtMessageError.text = message
    }
}