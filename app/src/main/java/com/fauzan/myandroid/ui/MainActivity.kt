package com.fauzan.myandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fauzan.myandroid.R
import kotlinx.android.synthetic.main.activity_main.*
import my.com.fauzan.androidarchitecture.utils.setupToolbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setupToolbar(main_layout, toolbar_view, "Dashboard")

        btn_rest_api.setOnClickListener {
            startActivity(Intent(this, RestActivity::class.java))
        }
    }
}
