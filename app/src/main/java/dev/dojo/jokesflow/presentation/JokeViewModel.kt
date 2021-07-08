package dev.dojo.jokesflow.presentation

import androidx.lifecycle.ViewModel
import dev.dojo.jokesflow.domain.usecase.GetNewJokeUseCase
import dev.dojo.jokesflow.domain.usecase.ListJokesUseCase

class JokeViewModel(
    private val getNewJokeUseCase: GetNewJokeUseCase,
    private val listJokesUseCase: ListJokesUseCase
) : ViewModel() {

    // expose a JokeUIState

    init {
        // list all jokes
    }

    fun onNewJoke() {
        // get new joke
    }
}
