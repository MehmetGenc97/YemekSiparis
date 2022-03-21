package com.example.proje.adapter

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.DialogUtils
import com.example.proje.R
import com.example.proje.databinding.SepetCardTasarimiBinding
import com.example.proje.databinding.YemekCardTasarimiBinding
import com.example.proje.entity.SepetYemekler
import com.example.proje.entity.Yemekler
import com.example.proje.fragment.SepetFragmentDirections
import com.example.proje.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class SepetYemeklerAdapter(var mContext: Context, var sepetYemeklerListesi: List<SepetYemekler>, var viewModel: SepetFragmentViewModel)
    : RecyclerView.Adapter<SepetYemeklerAdapter.SepetCardTasarimTutucu>() {
    private var loadingDialog: Dialog? = null

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
            Snackbar.make(it, "${sepetYemek.yemek_adi} sepetten silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    animationGoster()
                    viewModel.sepettenYemekSil(sepetYemek)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return sepetYemeklerListesi.size
    }

    private fun hideLoading() {
        loadingDialog?.let { if (it.isShowing) it.cancel() }
    }

    private fun showDialog() {
        hideLoading()
        loadingDialog = DialogUtils.showLoadingDialog(mContext, R.layout.yemek_sil)
    }

    private fun animationGoster() {
        showDialog()
        Handler().postDelayed({
            hideLoading()

        }, 2000)
    }

}