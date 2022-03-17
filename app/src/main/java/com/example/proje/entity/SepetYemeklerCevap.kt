package com.example.proje.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SepetYemeklerCevap(@SerializedName("sepet_yemekler") @Expose var sepetYemekler: List<SepetYemekler>,
                              @SerializedName("success") @Expose var success: Int) {
}