package dev.dojo.jokesflow.domain.usecase

import dev.dojo.jokesflow.domain.Joke
import dev.dojo.jokesflow.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow

typealias ListJokesUseCase = () -> Flow<List<Joke>>

fun listJokesUseCase(repository: JokeRepository): Flow<List<Joke>> {
    return repository.getAllJokes()
}
