package com.example.proje.viewmodel

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.example.proje.R
import com.example.proje.entity.Yemekler
import com.example.proje.repo.AnimasyonRepository
import com.example.proje.repo.YemeklerRepository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class YemekDetayFragmentViewModel : ViewModel() {
    val yrepo = YemeklerRepository()
    private lateinit var firebaseAuth: FirebaseAuth

    fun firebaseInit() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun sepeteYemekEkle(view: View, yemek: Yemekler, yemek_siparis_adet: Int, kullanici_adi: String) {
        firebaseInit()
        val firebaseUser = firebaseAuth.currentUser
        if(!firebaseUser?.email.isNullOrEmpty()) {
            AnimasyonRepository.animationGoster(view.context, R.layout.sepete_eklendi)
            Snackbar.make(view, "${yemek.yemek_adi} Sepete Eklendi", Snackbar.LENGTH_SHORT).show()
            yrepo.sepeteYemekEkle(yemek, yemek_siparis_adet, firebaseUser?.email.toString())
        } else {
            Snackbar.make(view, "Sepete Yemek Eklemek İçin Giriş Yapmalısınız!", Snackbar.LENGTH_SHORT).show()
        }
    }
}