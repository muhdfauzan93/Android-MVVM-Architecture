package my.com.fauzan.androidarchitecture.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.android.synthetic.main.toolbar.*

fun Activity.setupToolbar(
    mainLayout: View,
    toolbarView: View? = null,
    title: String = "",
    showBackArrow: Boolean = false
) {
    // Make status bar transparent
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            } else {
//                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            }
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }


    ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v: View?, insets: WindowInsetsCompat ->
        if (toolbarView != null) {
            (toolbarView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                0,
                insets.systemWindowInsetTop,
                0,
                0
            )
        }
        v!!.setPadding(0, 0, 0, insets.systemWindowInsetBottom)
        insets.consumeSystemWindowInsets()
    }

    if (toolbarView != null) {
        if (showBackArrow) {
            (this as AppCompatActivity).setSupportActionBar(toolbarView as Toolbar)
            (this.supportActionBar)!!.setDisplayShowHomeEnabled(true)
            (this.supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
            val paddingPixel = (72 * this.resources.displayMetrics.density).toInt()
            toolbarView.rootView.findViewById<TextView>(tv_toolbar_title.id)
                .setPadding(0, 0, paddingPixel, 0)
        }

        // set title
        toolbarView.rootView.findViewById<TextView>(tv_toolbar_title.id).text = title
    }
}
