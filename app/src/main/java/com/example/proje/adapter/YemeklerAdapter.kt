package com.example.proje.adapter

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.DialogUtils
import com.example.proje.R
import com.example.proje.databinding.YemekCardTasarimiBinding
import com.example.proje.entity.Yemekler
import com.example.proje.fragment.AnasayfaFragmentDirections
import com.example.proje.repo.AnimasyonRepository
import com.example.proje.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext: Context, var yemeklerListesi: List<Yemekler>, var viewModel: AnasayfaFragmentViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: YemekCardTasarimiBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: YemekCardTasarimiBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = YemekCardTasarimiBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        t.yemekNesnesi = yemek

        t.satirCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
        t.floatingActionButton2.setOnClickListener{
            Snackbar.make(it, "${yemek.yemek_adi} sepete eklensin mi", Snackbar.LENGTH_SHORT)
                .setAction("Evet") {
                    //animationGoster()

                    viewModel.sepeteEkle(it, yemek, 1)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}