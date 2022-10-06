package com.leshchenko.buttontoaction.ui.activity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.leshchenko.buttontoaction.R
import com.leshchenko.buttontoaction.data.entity.Action
import com.leshchenko.buttontoaction.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val button get() = findViewById<Button>(R.id.actionButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf("android.permission.POST_NOTIFICATIONS"), 1)
        }

        viewModel.actionStateFlow.collectWhenStarted(::setupButtonWithAction)

        viewModel.animationSharedFlow.collectWhenStarted { animate() }
        viewModel.toastSharedFlow.collectWhenStarted { showToast() }
    }

    private fun setupButtonWithAction(action: Action?) {
        with(button) {
            isEnabled = action != null
            if (action != null) {
                setText(action.type.nameRes)
                setOnClickListener { viewModel.onButtonClicked(action) }
            }
        }
    }

    private fun animate() {
        button
            .animate()
            .rotationBy(360f)
            .setDuration(300)
            .start()
    }

    private fun showToast() {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show()
    }

    private fun <T> Flow<T>.collectWhenStarted(collector: (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectWhenStarted.collect(collector)
            }
        }
    }
}