package com.example.proje.repo

import android.app.Dialog
import android.content.Context
import android.os.Handler
import com.example.proje.DialogUtils

class AnimasyonRepository {
    companion object {
        var loadingDialog: Dialog? = null

        fun dialogGizle() {
            loadingDialog?.let { if (it.isShowing) it.cancel() }
        }

        fun dialogGoster(context: Context, layoutResId: Int) {
            dialogGizle()
            loadingDialog = DialogUtils.showLoadingDialog(context, layoutResId)
        }

        fun animationGoster(context: Context, layoutResId: Int) {
            dialogGoster(context, layoutResId)
            Handler().postDelayed({
                dialogGizle()
            }, 2000)
        }
    }
}