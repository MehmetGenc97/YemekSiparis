package com.example.proje.retrofit



class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"
        val BASE_URL_RESIM = "http://kasimadalan.pe.hu/yemekler/resimler/"

        fun getYemeklerDaoInterface() : YemeklerDaoInterface {
            return RetrofitClient.getClient(BASE_URL)
                .create(YemeklerDaoInterface::class.java)
        }
    }
}