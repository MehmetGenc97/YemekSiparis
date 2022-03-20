package com.example.proje.fragment.firebase

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.proje.R
import com.example.proje.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var tasarim: FragmentSignUpBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        tasarim.kayitOlFragment = this
        tasarim.kayitOlToolbarBaslik = "Kaydol"

        progressDialogAyarla()

        firebaseAuth = FirebaseAuth.getInstance()

        return tasarim.root
    }

    private fun progressDialogAyarla() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Lütfen Bekleyin")
        progressDialog.setMessage("Kayıt yapılıyor...")
        progressDialog.setCanceledOnTouchOutside(false)
    }

    fun butonKayitTikla(email: String, parola: String) {
        veriyiKontrolEt(email, parola)
    }

    fun textViewGirisYapTikla(view: View) {
        Navigation.findNavController(view).navigate(R.id.kayitGirisGecis)
    }

    private fun veriyiKontrolEt(email: String, parola: String) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tasarim.editTextEmailEdit.error = "Hatalı Email"
        } else if(TextUtils.isEmpty(parola)) {
            tasarim.textInputParola.error = "Parola Boş Olamaz"
        } else if(parola.length < 6) {
            tasarim.textInputParola.error = "Parola 6 karakterden oluşmalıdır."
        } else {
            firebaseKaydol(email, parola)
        }
    }

    private fun firebaseKaydol(email: String, parola: String) {
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, parola)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Snackbar.make(tasarim.root, "$email ile kayıt olundu.", Snackbar.LENGTH_SHORT).show()
                Navigation.findNavController(tasarim.root).navigate(R.id.kayitHesapGecis)
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Snackbar.make(tasarim.root, "Kayıt Başarısız", Snackbar.LENGTH_SHORT).show()
            }
    }
}