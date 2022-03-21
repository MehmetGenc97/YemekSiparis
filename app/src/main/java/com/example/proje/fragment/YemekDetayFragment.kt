package com.example.proje.fragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.proje.DialogUtils
import com.example.proje.R
import com.example.proje.databinding.FragmentYemekDetayBinding
import com.example.proje.entity.Yemekler
import com.example.proje.viewmodel.YemekDetayFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class YemekDetayFragment : Fragment() {
    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayFragmentViewModel
    private var loadingDialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false)
        tasarim.yemekDetayFragment = this
        tasarim.yemekDetayToolbarBaslik = "Yemek Detay"
        tasarim.yemekAdet = 1

        val bundle: YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        tasarim.yemekNesnesi = gelenYemek
        //resimGoster(tasarim.imageView2, gelenYemek.yemek_resim_adi)
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemekDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel

    }

    fun sepeteEkle(view: View, yemek: Yemekler, yemek_siparis_adet: Int, kullanici_adi: String){

        Snackbar.make(view, "${yemek.yemek_adi} Sepete ${yemek_siparis_adet} tane eklensin mi?", Snackbar.LENGTH_LONG)
            .setAction("Evet") {
                animationGoster()
                viewModel.sepeteYemekEkle(yemek, yemek_siparis_adet, kullanici_adi)
                Snackbar.make(view, "${yemek.yemek_adi} Sepete Eklendi", Snackbar.LENGTH_SHORT).show()
            }.show()
    }

    fun adetEkle() {
        tasarim.yemekAdet ++
    }

    fun adetCikar() {
        if(tasarim.yemekAdet != 1) {
            tasarim.yemekAdet --
        }
    }

    private fun hideLoading() {
        loadingDialog?.let { if (it.isShowing) it.cancel() }
    }

    private fun showDialog() {
        hideLoading()
        loadingDialog = DialogUtils.showLoadingDialog(requireContext(), R.layout.sepete_eklendi)
    }

    private fun animationGoster() {
        showDialog()
        Handler().postDelayed({
            hideLoading()

        }, 2500)
    }
}