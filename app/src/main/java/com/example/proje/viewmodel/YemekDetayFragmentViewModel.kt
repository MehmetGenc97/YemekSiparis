package com.example.proje.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.example.proje.entity.Yemekler
import com.example.proje.repo.YemeklerRepository

class YemekDetayFragmentViewModel : ViewModel() {
    val yrepo = YemeklerRepository()

    fun sepeteYemekEkle(yemek: Yemekler, yemek_siparis_adet: Int, kullanici_adi: String) {
        yrepo.sepeteYemekEkle(yemek, yemek_siparis_adet, kullanici_adi)
    }
}