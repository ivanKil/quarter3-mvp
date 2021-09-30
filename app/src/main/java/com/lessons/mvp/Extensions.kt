package com.lessons.mvp

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.addTo(disposables: CompositeDisposable) {
    disposables.add(this)
}