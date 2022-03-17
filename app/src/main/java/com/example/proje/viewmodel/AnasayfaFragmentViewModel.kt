package com.example.proje.viewmodel

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proje.entity.Yemekler
import com.example.proje.repo.YemeklerRepository

class AnasayfaFragmentViewModel : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    val yrepo = YemeklerRepository()
    val kullanici_adi = "Genç"

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

    fun yemekleriYukle() {
        yrepo.tumYemekleriAl()
    }

    fun sepeteEkle(yemek: Yemekler, yemek_siparis_adet: Int, kullanici_adi: String) {
        yrepo.sepeteYemekEkle(yemek, yemek_siparis_adet, kullanici_adi)
    }
}