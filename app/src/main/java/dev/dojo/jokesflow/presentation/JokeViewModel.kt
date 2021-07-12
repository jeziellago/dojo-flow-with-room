package dev.dojo.jokesflow.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dojo.jokesflow.domain.usecase.GetNewJokeUseCase
import dev.dojo.jokesflow.domain.usecase.ListJokesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel(
    private val getNewJokeUseCase: GetNewJokeUseCase,
    private val listJokesUseCase: ListJokesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val jokeState = MutableStateFlow(JokeUIState())

    val state: StateFlow<JokeUIState>
        get() = jokeState

    init {
        viewModelScope.launch {
            listJokesUseCase()
                .flowOn(dispatcher)
                .onStart {
                    jokeState.value = jokeState.value.copy(isLoading = true)
                }
                .catch { error ->
                    jokeState.value = jokeState.value.copy(error = error.message, isLoading = false)
                }
                .collect { list ->
                    jokeState.value = jokeState.value.copy(jokes = list, error = null, isLoading = false)
                }
        }
    }

    fun onNewJoke() {
        viewModelScope.launch {
            jokeState.value = jokeState.value.copy(isLoading = true)

            runCatching {
                withContext(dispatcher) { getNewJokeUseCase() }
            }.onFailure { error ->
                jokeState.value = jokeState.value.copy(error = error.message, isLoading = false)
            }.onSuccess {
                jokeState.value = jokeState.value.copy(isLoading = false)
            }
        }
    }
}
