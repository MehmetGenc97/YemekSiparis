package com.example.proje.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.databinding.SepetCardTasarimiBinding
import com.example.proje.databinding.YemekCardTasarimiBinding
import com.example.proje.entity.SepetYemekler
import com.example.proje.entity.Yemekler
import com.example.proje.fragment.SepetFragmentDirections
import com.example.proje.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class SepetYemeklerAdapter(var mContext: Context, var sepetYemeklerListesi: List<SepetYemekler>, var viewModel: SepetFragmentViewModel)
    : RecyclerView.Adapter<SepetYemeklerAdapter.SepetCardTasarimTutucu>() {

    inner class SepetCardTasarimTutucu(tasarim: SepetCardTasarimiBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: SepetCardTasarimiBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = SepetCardTasarimiBinding.inflate(layoutInflater, parent, false)
        return SepetCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepetYemek = sepetYemeklerListesi.get(position)
        val t = holder.tasarim
        t.sepetYemekNesnesi = sepetYemek
        val yemek = Yemekler(0, sepetYemek.yemek_adi, sepetYemek.yemek_resim_adi, sepetYemek.yemek_fiyat)

        t.sepetCard.setOnClickListener {
            val gecis = SepetFragmentDirections.sepetDetayGecis(yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
        t.imageViewSepetSil.setOnClickListener{
            Snackbar.make(it, "${yemek.yemek_adi} sepetten silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    viewModel.sepettenYemekSil(sepetYemek)
                }.show()
            viewModel.sepettekiYemekleriYukle(viewModel.kullanici_adi)
        }
    }

    override fun getItemCount(): Int {
        return sepetYemeklerListesi.size
    }

}