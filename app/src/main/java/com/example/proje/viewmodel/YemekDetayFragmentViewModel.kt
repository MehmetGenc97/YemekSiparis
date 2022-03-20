package com.example.proje.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.example.proje.entity.Yemekler
import com.example.proje.repo.YemeklerRepository
import com.google.firebase.auth.FirebaseAuth

class YemekDetayFragmentViewModel : ViewModel() {
    val yrepo = YemeklerRepository()
    private lateinit var firebaseAuth: FirebaseAuth

    fun firebaseInit() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun sepeteYemekEkle(yemek: Yemekler, yemek_siparis_adet: Int, kullanici_adi: String) {
        firebaseInit()
        val firebaseUser = firebaseAuth.currentUser
        if(!firebaseUser?.email.isNullOrEmpty())
            yrepo.sepeteYemekEkle(yemek, yemek_siparis_adet, firebaseUser?.email.toString())
    }
}