package com.harsh.githubClient.ui.adapter

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.harsh.githubClient.R

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindUrl(imageUrl: String?) {
    imageUrl?.let {
        val requestOptions = RequestOptions()
        val width: Int = context.resources.getDimensionPixelOffset(R.dimen.dp200)
        val height: Int = context.resources.getDimensionPixelOffset(R.dimen.dp80)
        requestOptions.override(width, height)
        val factory =
            DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_placeholder)
            .apply(requestOptions)
            .into(this)
    }
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter(value = ["setupVisibility"])
fun ProgressBar.progressVisibility(isLoading: Boolean) {
    isLoading.let {
        isVisible = isLoading
    }
}