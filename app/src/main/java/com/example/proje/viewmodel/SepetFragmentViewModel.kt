package com.example.proje.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proje.entity.SepetYemekler
import com.example.proje.entity.Yemekler
import com.example.proje.repo.YemeklerRepository

class SepetFragmentViewModel : ViewModel() {
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    val yrepo = YemeklerRepository()
    val kullanici_adi = "Genç" // burası ile ilgilenmen gerek
    var toplamFiyat = 0

    init {
        sepettekiYemekleriYukle(kullanici_adi)
        sepetYemeklerListesi = yrepo.sepetiGetir()
        toplamFiyat = yrepo.toplamSepetFiyatiGetir()
    }

    fun sepettekiYemekleriYukle(kullanici_adi: String) {
        yrepo.sepettekiYemekleriAl(kullanici_adi)
    }

    fun sepettenYemekSil(sepetYemekler: SepetYemekler) {
        yrepo.sepettenYemekSil(sepetYemekler)
    }

    fun toplamFiyatHesapla() {
        yrepo.toplamFiyatHesapla()
    }
}