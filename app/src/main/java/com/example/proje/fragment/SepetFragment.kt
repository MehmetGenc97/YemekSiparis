package com.example.proje.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.proje.R
import com.example.proje.adapter.SepetYemeklerAdapter
import com.example.proje.adapter.YemeklerAdapter
import com.example.proje.adapter.yemekResimYukle
import com.example.proje.databinding.FragmentSepetBinding
import com.example.proje.viewmodel.SepetFragmentViewModel

class SepetFragment : Fragment() {
    private lateinit var tasarim: FragmentSepetBinding
    private lateinit var viewModel: SepetFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_sepet, container, false)
        tasarim.sepetFragment = this
        tasarim.sepetToolbarBaslik = "Sepetim"


        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) {
            val adapter = SepetYemeklerAdapter(requireContext(), it, viewModel)
            tasarim.sepetAdapter = adapter
            tasarim.textViewToplamSepetFiyat.text = "${toplamFiyatHesapla().toString()} ₺"
        }


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepettekiYemekleriYukle(viewModel.kullanici_adi)
    }

    fun toplamFiyatHesapla() : Int {
        var toplamFiyat = 0

        if(!viewModel.sepetYemeklerListesi.value.isNullOrEmpty()) {
            for(yemek in viewModel.sepetYemeklerListesi.value!!.iterator()) {
                toplamFiyat += yemek.yemek_siparis_adet * yemek.yemek_fiyat
            }
        } else {
            toplamFiyat = 0
        }

        return toplamFiyat
    }

    fun siparisVer() {

    }
}