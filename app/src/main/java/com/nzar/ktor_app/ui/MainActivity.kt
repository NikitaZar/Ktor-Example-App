package com.nzar.ktor_app.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.nzar.ktor_app.R
import com.nzar.ktor_app.adapter.SimpleWithoutPaddingAdapter
import com.nzar.ktor_app.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setMethodSpinner()
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
                    queryText = etQuery.text.toString()
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
                            R.string.close_text
                        ) {}.show()
                    }
                    if (state.isRequestError) {
                        AlertDialog.Builder(this@MainActivity).apply {
                            setMessage(R.string.request_error_message)
                            setPositiveButton(R.string.close_text) { _, _ -> }
                        }.show()
                    }
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.title = getString(R.string.app_title)
        binding.toolbar.setTitleTextColor(getColor(R.color.white))
        setSupportActionBar(binding.toolbar)
    }

    private fun setMethodSpinner() {
        binding.spMethod.apply {
            adapter = SimpleWithoutPaddingAdapter(
                context = this@MainActivity,
                items = context.resources.getStringArray(R.array.request_method) as Array<CharSequence>
            )
        }
    }
}