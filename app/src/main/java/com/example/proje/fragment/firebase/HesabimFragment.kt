package com.example.proje.fragment.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.proje.R
import com.example.proje.databinding.FragmentHesabimBinding
import com.google.firebase.auth.FirebaseAuth

class HesabimFragment : Fragment() {
    private lateinit var tasarim: FragmentHesabimBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_hesabim, container, false)

        tasarim.hesabimFragment = this
        tasarim.hesabimToolbarBaslik = "Hesabım"

        return tasarim.root
    }

    fun butonCikisYapTikla() {
        firebaseAuth.signOut()
        kullaniciyiKontrolEt()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        kullaniciyiKontrolEt()
    }

    private fun kullaniciyiKontrolEt() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null) {
            tasarim.kullaniciEmail = firebaseUser.email
        } else {
            Navigation.findNavController(tasarim.root).navigate(R.id.hesapGirisGecis)
        }
    }
}