package com.fauzan.myandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fauzan.myandroid.R
import com.fauzan.myandroid.model.entity.Post
import com.fauzan.myandroid.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.activity_room.*
import my.com.fauzan.androidarchitecture.utils.setupToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class RoomActivity : AppCompatActivity() {
    // Instantiate viewModel with Koin
    private val roomViewModel: RoomViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        this.setupToolbar(main_layout, toolbar_view, "Room DB", true)
        setupView()
    }

    private fun setupView() {
        roomViewModel.posts.observe(this, Observer {
            Timber.e(it.size.toString())
            it.forEach { post ->
                Timber.d(post.title)
            }
        })

        roomViewModel.insertPost(Post(0, "1", 1, "Ohaiyo", 2))
        roomViewModel.insertPost(Post(0, "1", 1, "Sekai", 2))
        roomViewModel.insertPost(Post(0, "1", 1, "good", 2))
        roomViewModel.insertPost(Post(0, "1", 1, "morning", 2))

        roomViewModel.getAllPosts()

    }
}
