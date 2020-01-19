package com.fauzan.myandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import my.com.fauzan.androidarchitecture.utils.setupToolbar
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setupToolbar(main_layout, toolbar_view, "Dashboard", true)
    }
}
