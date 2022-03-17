package com.example.proje.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.proje.retrofit.ApiUtils
import com.squareup.picasso.Picasso

@BindingAdapter("yemekResimYukle")
fun yemekResimYukle(imageView: ImageView, yemekResimAdi: String) {
    Picasso.get().load("${ApiUtils.BASE_URL_RESIM}$yemekResimAdi").into(imageView)
}