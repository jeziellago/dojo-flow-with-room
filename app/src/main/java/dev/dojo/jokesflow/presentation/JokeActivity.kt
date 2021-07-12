package dev.dojo.jokesflow.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.dojo.jokesflow.databinding.ActivityJokeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokeActivity : AppCompatActivity() {

    private val binding: ActivityJokeBinding by lazy {
        ActivityJokeBinding.inflate(layoutInflater).apply { setContentView(root) }
    }

    private val jokeViewModel: JokeViewModel by viewModel()

    private val jokesAdapter by lazy {
        JokesAdapter { joke ->
            // show joke detail
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            jokesRecyclerView.adapter = jokesAdapter
            btnGetNewJoke.setOnClickListener {
                jokeViewModel.onNewJoke()
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                jokeViewModel.state.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: JokeUIState) {
        binding.progressBar.isVisible = state.isLoading

        state.jokes
            .takeIf { it.isNotEmpty() }
            .run { jokesAdapter.submitList(state.jokes) }

        if (state.error != null) {
            Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
        }
    }
}
