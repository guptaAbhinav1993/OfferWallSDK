package com.brandmatic.offerwall.helper

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.brandmatic.offerwall.R

// name - Abhinav Gupta
// created at 13th Feb 2024

class ProgressDialog {

    companion object {

        private var progressDialog: Dialog? = null

        fun start(context: Context) {
            if (!isShowing()) {
                if (!(context as Activity).isFinishing) {
                    progressDialog = Dialog(context)
                    progressDialog!!.setCancelable(false)
                    progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    progressDialog!!.setContentView(R.layout.view_progress_dialog)
                    progressDialog!!.show()
                }
            }
        }


        fun dismiss() {
            try {
                if (progressDialog != null && progressDialog!!.isShowing) {
                    progressDialog!!.dismiss()
                }
            } catch (e: IllegalArgumentException) {
                // Handle or log or ignore
            } catch (e: Exception) {
                // Handle or log or ignore
            } finally {
                progressDialog = null
            }
        }


        fun isShowing(): Boolean {
            return if (progressDialog != null) {
                progressDialog!!.isShowing
            } else {
                false
            }
        }
    }
}