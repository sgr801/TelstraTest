package com.shekh.test.telstra

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shekh.test.telstra.helpers.Injector
import com.shekh.test.telstra.model.PhotoResponse
import com.shekh.test.telstra.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the view model
        viewModel = ViewModelProviders.of(this, Injector.provideViewModelFactory(this))
                .get(MainViewModel::class.java)
        initAdapter()

    }

    private fun initAdapter() {
        viewModel.photos.observe(this, Observer<PhotoResponse> {
            t1.text = it?.title
            t2.text = it?.rows?.count()?.toString()
        })
        viewModel.networkError.observe(this, Observer<String> {
            Toast.makeText(this, "Wooops $it", Toast.LENGTH_LONG).show()
        })
        viewModel.getPhotos()
    }
}
