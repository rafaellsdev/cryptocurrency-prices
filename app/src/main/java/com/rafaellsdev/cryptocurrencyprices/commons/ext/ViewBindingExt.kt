package com.rafaellsdev.cryptocurrencyprices.commons.ext

import android.view.View

fun View.onClick(listenerFunction: () -> Unit) {
    this.setOnClickListener { listenerFunction.invoke() }
}