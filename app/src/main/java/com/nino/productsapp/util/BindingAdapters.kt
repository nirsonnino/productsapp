package com.nino.productsapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.*

@BindingAdapter("thumbnail")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide
            .with(view.context)
            .load(url)
            .into(view)
    }
}