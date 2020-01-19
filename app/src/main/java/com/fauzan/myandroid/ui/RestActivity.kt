package com.fauzan.myandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.fauzan.myandroid.R
import com.fauzan.myandroid.model.entity.Status
import com.fauzan.myandroid.viewmodel.RestViewModel
import kotlinx.android.synthetic.main.activity_rest.*
import my.com.fauzan.androidarchitecture.utils.setupToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RestActivity : AppCompatActivity() {

    // Instantiate viewModel with Koin
    private val restViewModel: RestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest)

        this.setupToolbar(main_layout, toolbar_view, "Rest API", true)
        restViewModel.fetchAllUserData()
        restViewModel.fetchUserData(2)

        observerViewModel()
    }

    private fun observerViewModel() {
        restViewModel.getAllUsersData().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    Timber.e(it.data!![0].name)
                }
                Status.ERROR -> {}
            }
        })

        restViewModel.getUserData().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    Timber.e(it.data!!.name)
                }
                Status.ERROR -> {}
            }
        })
    }
}
