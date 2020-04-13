package com.safa.kotlinexample.util

import android.content.Context
import android.widget.ImageView

import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.safa.kotlinexample.R


fun ImageView.downloadImage(url: String?, placeholderDrawable: CircularProgressDrawable){

    val option = RequestOptions()
        .placeholder(placeholderDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(this)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}

fun PlaceholderDrawable(context: Context): CircularProgressDrawable{

    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}