package com.ezatpanah.koindi_retrofit_youtube.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.koindi_retrofit_youtube.adapter.PhotoAdapter
import com.ezatpanah.koindi_retrofit_youtube.databinding.ActivityMainBinding
import com.ezatpanah.koindi_retrofit_youtube.utils.DataStatus
import com.ezatpanah.koindi_retrofit_youtube.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewModel: MainViewModel by inject()
    private val photosAdapter by lazy { PhotoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setupRecyclerView()

        lifecycleScope.launch {

            binding.apply {
                viewModel.getPhoto("flower")
                viewModel.photoList.observe(this@MainActivity) {
                    when (it.status) {
                        DataStatus.Status.LOADING -> {
                            showProgressBar(false)
                        }
                        DataStatus.Status.SUCCESS -> {
                            showProgressBar(true)
                            photosAdapter.differ.submitList(it.data)
                        }

                        DataStatus.Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(
                                this@MainActivity,
                                "There is something wrong!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

    }

    private fun showProgressBar(isShown: Boolean) {
        binding!!.apply {
            if (isShown) {
                rvPhoto.visibility = View.VISIBLE
                pBarLoading.visibility = View.GONE
            } else {
                rvPhoto.visibility = View.GONE
                pBarLoading.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        binding!!.rvPhoto.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = photosAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}