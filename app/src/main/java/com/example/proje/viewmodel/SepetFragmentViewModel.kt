package com.example.proje.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proje.entity.SepetYemekler
import com.example.proje.entity.Yemekler
import com.example.proje.repo.YemeklerRepository
import com.google.firebase.auth.FirebaseAuth

class SepetFragmentViewModel : ViewModel() {
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    val yrepo = YemeklerRepository()
    private lateinit var firebaseAuth: FirebaseAuth
    var toplamFiyat = 0

    init {
        sepettekiYemekleriYukle()
        sepetYemeklerListesi = yrepo.sepetiGetir()
        toplamFiyat = yrepo.toplamSepetFiyatiGetir()
    }

    fun sepettekiYemekleriYukle() {
        firebaseInit()
        val firebaseUser = firebaseAuth.currentUser
        if(!firebaseUser?.email.isNullOrEmpty())
            yrepo.sepettekiYemekleriAl(firebaseUser?.email.toString())
    }

    fun sepettenYemekSil(sepetYemekler: SepetYemekler) {
        yrepo.sepettenYemekSil(sepetYemekler)
        sepettekiYemekleriYukle()
    }

    fun firebaseInit() {
        firebaseAuth = FirebaseAuth.getInstance()
    }
}