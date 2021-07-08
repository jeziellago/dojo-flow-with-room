package dev.dojo.jokesflow.presentation

import dev.dojo.jokesflow.domain.Joke

data class JokeUIState(
    val isLoading: Boolean = false,
    val jokes: List<Joke> = emptyList(),
    val error: String? = null
)
