package com.lessons.mvp.presentation.userlist

import android.widget.ImageView
import com.bumptech.glide.Glide

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}

