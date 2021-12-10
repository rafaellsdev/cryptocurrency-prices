package com.rafaellsdev.cryptocurrencyprices.commons.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this) {
        it?.let { action(it) }
    }
}

fun <T> LiveData<T>.emit(value: T) {
    require(this is MutableLiveData) { "$this isn't a valid MutableLiveData instance" }
    this.value = value
}