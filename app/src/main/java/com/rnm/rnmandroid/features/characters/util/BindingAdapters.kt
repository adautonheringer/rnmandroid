package com.rnm.rnmandroid.features.characters.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(
    "srcUrl",
    "placeholder",
    requireAll = false // make the attributes optional
)
fun ImageView.bindSrcUrl(
    url: String,
    placeholder: Drawable?,
) = Glide.with(this).load(url).let { request ->
    if (placeholder != null) { request.placeholder(placeholder) }
    request.into(this)
}

