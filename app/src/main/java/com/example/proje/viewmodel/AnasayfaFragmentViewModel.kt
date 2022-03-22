package com.example.proje.viewmodel

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proje.R
import com.example.proje.entity.Yemekler
import com.example.proje.repo.AnimasyonRepository
import com.example.proje.repo.YemeklerRepository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import javax.sql.StatementEvent

class AnasayfaFragmentViewModel : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    val yrepo = YemeklerRepository()
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var kullanici_adi: String

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

    fun yemekleriYukle() {
        yrepo.tumYemekleriAl()
    }

    fun sepeteEkle(view: View, yemek: Yemekler, yemek_siparis_adet: Int) {
        firebaseInit()
        val firebaseUser = firebaseAuth.currentUser
        if(!firebaseUser?.email.isNullOrEmpty()) {
            yrepo.sepeteYemekEkle(yemek, yemek_siparis_adet, firebaseUser!!.email.toString())
            AnimasyonRepository.animationGoster(view.context, R.layout.sepete_eklendi)
        } else
            Snackbar.make(view, "Sepete ürün eklemek için Giriş yapın", Snackbar.LENGTH_SHORT).show()
    }

    fun firebaseInit() {
        firebaseAuth = FirebaseAuth.getInstance()
    }
}