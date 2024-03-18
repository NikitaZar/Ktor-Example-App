package com.nzar.ktor_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.nzar.ktor_app.R
import com.nzar.ktor_app.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observe()
        listeners()
    }

    private fun listeners() {
        with(binding) {
            btRequest.setOnClickListener {
                viewModel.request(
                    url = etUrl.text.toString(),
                    methodPosition = spMethod.selectedItemPosition,
                    body = etBody.text.toString(),
                    query = etQuery.text.toString()
                )
            }
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect { state ->
                with(binding) {
                    progress.isVisible = state.inProgress
                    state.status?.let { status ->
                        Snackbar.make(
                            binding.root,
                            this@MainActivity.getString(R.string.status_message, status),
                            Snackbar.LENGTH_INDEFINITE
                        ).setAction(
                            R.string.snack_bar_button_text
                        ) {}.show()
                    }
                }
            }
        }
    }
}