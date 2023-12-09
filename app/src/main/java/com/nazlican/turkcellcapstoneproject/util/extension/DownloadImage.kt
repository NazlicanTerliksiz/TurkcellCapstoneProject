package com.nazlican.turkcellcapstoneproject.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.downloadFromUrl(url: String){
    Glide.with(this)
        .load(url)
        .into(this)
}