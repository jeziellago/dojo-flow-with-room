package dev.dojo.jokesflow.domain.repository

import dev.dojo.jokesflow.domain.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    suspend fun createJoke()
    fun getAllJokes(): Flow<List<Joke>>
}