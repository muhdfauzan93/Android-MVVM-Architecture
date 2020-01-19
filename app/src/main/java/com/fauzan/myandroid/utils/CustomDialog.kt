package com.fauzan.myandroid.utils

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.cardview.widget.CardView
import com.fauzan.myandroid.R
import com.github.ajalt.timberkt.Timber
import kotlinx.android.synthetic.main.ui_custom_dialog.*


class CustomDialog(context: Context, title: String = "", message: String = "", icon: Int = 0) :
    Dialog(context, R.style.CustomDialogTheme) {
    private val Tag = CustomDialog::class.java.simpleName

    init {
        setContentView(R.layout.ui_custom_dialog)
        val window = window
        if (window != null) window.attributes.gravity = 17

        window.apply {
            // Set dialog width = 75% screen width
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            this!!.decorView.findViewById<CardView>(R.id.cv_custom_dialog).layoutParams.width =
                (0.75 * screenWidth).toInt()

            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }.also {
            Timber.tag(Tag).d("Custom dialog initiated")
        }

        tv_title.text = title
        tv_message.text = message
        iv_icon.setImageResource(android.R.color.darker_gray)
    }

    fun setOnClick(onClickListener: View.OnClickListener) {
        tv_ok.setOnClickListener(onClickListener)
    }

}