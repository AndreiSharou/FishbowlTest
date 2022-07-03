package com.example.fishbowltest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.fishbowltest.databinding.ActivityMainBinding
import com.example.fishbowltest.util.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

//    private val viewModel by viewModels<MainViewModel>()
//    private val viewModel: MainViewModel by viewModels()
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = MainAdapter()
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(2)) {
                    viewModel.loadNewPage()
                }
            }
        })

        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collect { state ->

                (binding.recycler.adapter as MainAdapter).submitList(
                    state.postList
                )
                when (state.screenState) {
                    ScreenState.IDLE -> {
                        binding.progress.isVisible = false
                    }
                    ScreenState.LOADING -> {
                        binding.progress.isVisible = true
                    }
                    else -> {
                        binding.progress.isVisible = false
                        Toast.makeText(
                            this@MainActivity,
                            state.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }
}