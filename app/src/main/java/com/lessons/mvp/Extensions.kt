package com.lessons.mvp

import android.view.View
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun View.click(click: () -> Unit) = setOnClickListener { click() }
fun Disposable.addTo(disposables: CompositeDisposable) {
    disposables.add(this)
}