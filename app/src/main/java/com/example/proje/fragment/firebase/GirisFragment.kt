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
import com.example.proje.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var tasarim: FragmentLoginBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        tasarim.girisFragment = this
        tasarim.girisToolbarBaslik = "Giriş"

        progressDialogYapilandir()

        return tasarim.root
    }

    private fun progressDialogYapilandir() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Lütfen Bekleyin")
        progressDialog.setMessage("Giriş yapılıyor...")
        progressDialog.setCanceledOnTouchOutside(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAyarla(view)
    }

    private fun firebaseAyarla(view: View) {
        firebaseAuth = FirebaseAuth.getInstance()

        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null) {
            Navigation.findNavController(view).navigate(R.id.girisHesapGecis)
        }
    }

    fun butonGirisTikla(email: String, parola: String) {
        veriyiKontrolEt(email, parola)
    }

    fun textviewHesapYokTikla(view: View) {
        Navigation.findNavController(view).navigate(R.id.girisKayitGecis)
    }

    private fun veriyiKontrolEt(email: String, parola: String) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tasarim.editTextEmailEdit.error = "Hatalı Email"
        } else if(TextUtils.isEmpty(parola)) {
            tasarim.textInputParola.error = "Lütfen Parolayı Giriniz!"
        } else {
            firebaseGirisYap(email, parola)
        }
    }

    private fun firebaseGirisYap(email: String, parola: String) {
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, parola)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Snackbar.make(tasarim.root, "$email ile giriş yapıldı", Snackbar.LENGTH_SHORT).show()
                Navigation.findNavController(tasarim.root).navigate(R.id.girisHesapGecis)
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Snackbar.make(tasarim.root, "Giriş başarısız lütfen bilgilerinizi kontrol ediniz.", Snackbar.LENGTH_SHORT).show()
            }
    }
}