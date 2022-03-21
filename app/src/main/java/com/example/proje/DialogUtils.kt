package com.example.proje

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable

class DialogUtils {
    companion object {
        fun showLoadingDialog(context: Context, layoutResId: Int) : Dialog {
            val progressDialog = Dialog(context)

            progressDialog.let {
                it.show()
                it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
                it.setContentView(layoutResId)
                it.setCancelable(false)
                it.setCanceledOnTouchOutside(false)

                return it
            }
        }
    }
}