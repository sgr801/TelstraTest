package com.shekh.test.telstra

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shekh.test.telstra.helpers.Injector
import com.shekh.test.telstra.model.PhotoResponse
import com.shekh.test.telstra.ui.PhotosAdapter
import com.shekh.test.telstra.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, Injector.provideViewModelFactory(applicationContext))
                .get(MainViewModel::class.java)

        setupRecyclerView()
        initAdapter()
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPhotos()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun initAdapter() {
        viewModel.photos.observe(this, Observer<PhotoResponse> {
            swipeRefreshLayout.isRefreshing = false
            it?.title?.let { title ->
                setActionBarTitle(title)
            }

            it?.rows?.let { rows ->
                photosAdapter = PhotosAdapter(this, rows)
                recyclerView.adapter = photosAdapter
            }

            loadingTextView.visibility = if (it?.rows?.isEmpty() == true) View.VISIBLE else View.GONE
        })

        viewModel.networkError.observe(this, Observer<String> {
            swipeRefreshLayout.isRefreshing = false
            loadingTextView.text = it
            Toast.makeText(this, "Wooops $it", Toast.LENGTH_LONG).show()
        })

        viewModel.getPhotos()
        loadingTextView.text = this.getString(R.string.loading)
        swipeRefreshLayout.isRefreshing = true
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}
