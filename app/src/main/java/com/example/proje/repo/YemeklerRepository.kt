package com.example.proje.repo

import android.util.Log
import android.widget.ImageView
import androidx.core.util.rangeTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proje.entity.*
import com.example.proje.retrofit.ApiUtils
import com.example.proje.retrofit.YemeklerDaoInterface
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerRepository {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var sepetYemeklerListesi: MutableLiveData<List<SepetYemekler>>
    var ydao : YemeklerDaoInterface
    private var toplamSepetFiyati = 0

    init {
        ydao = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
        sepetYemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun sepetiGetir() : MutableLiveData<List<SepetYemekler>> {
        return sepetYemeklerListesi
    }

    fun toplamSepetFiyatiGetir() : Int {
        return toplamSepetFiyati
    }

    fun tumYemekleriAl() {
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun sepettekiYemekleriAl(kullanici_adi: String) {
        ydao.sepettekiYemekleriGetir(kullanici_adi).enqueue(object : Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>?, response: Response<SepetYemeklerCevap>) {
                val liste = response.body().sepetYemekler
                sepetYemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun sepeteYemekEkle(yemek: Yemekler, yemek_siparis_adet: Int, kullanici_adi: String){
        ydao.sepeteYemekEkle(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            .enqueue(object : Callback<CRUDCevap>{
                override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {}
                override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
            })
    }

    fun sepettenYemekSil(sepetYemek: SepetYemekler) {
        ydao.sepettenYemekSil(sepetYemek.sepet_yemek_id, sepetYemek.kullanici_adi).enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {}
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }
}