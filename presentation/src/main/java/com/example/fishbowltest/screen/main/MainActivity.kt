package com.example.fishbowltest.screen.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.fishbowltest.util.ScreenState
import com.example.fishbowltest.view.MainRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collect { state ->
                setContent {
                    ApplyState(state)
                }
            }
        }
    }

    @Composable
    fun ApplyState(state: MainUiState) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            MainRecyclerView(state = state.list, onScrollToEnd = { viewModel.loadNewPage() })
            when (state.screenState) {
                ScreenState.LOADING -> {
                    CircularProgressIndicator()
                }
                ScreenState.ERROR -> {
                    Toast.makeText(
                        this@MainActivity,
                        state.error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }
}