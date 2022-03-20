package com.example.proje.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Yemekler(@SerializedName("yemek_id") @Expose val yemek_id: Int,
                    @SerializedName("yemek_adi") @Expose val yemek_adi: String,
                    @SerializedName("yemek_resim_adi") @Expose val yemek_resim_adi: String,
                    @SerializedName("yemek_fiyat") @Expose val yemek_fiyat: Int) : Serializable {
}