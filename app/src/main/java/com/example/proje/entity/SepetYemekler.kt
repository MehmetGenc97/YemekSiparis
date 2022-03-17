package com.example.proje.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SepetYemekler(@SerializedName("sepet_yemek_id") @Expose val sepet_yemek_id: Int,
                         @SerializedName("yemek_adi") @Expose val yemek_adi: String,
                         @SerializedName("yemek_resim_adi") @Expose val yemek_resim_adi: String,
                         @SerializedName("yemek_fiyat") @Expose val yemek_fiyat: Int,
                         @SerializedName("yemek_siparis_adet") @Expose val yemek_siparis_adet: Int,
                         @SerializedName("kullanici_adi") @Expose val kullanici_adi: String) : Serializable {
}