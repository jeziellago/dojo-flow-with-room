package dev.dojo.jokesflow.data.repository

import dev.dojo.jokesflow.data.remote.JokeService
import dev.dojo.jokesflow.data.local.JokeDao
import dev.dojo.jokesflow.domain.repository.JokeRepository

internal class JokeRepositoryImpl(
    private val jokeService: JokeService,
    private val jokeDao: JokeDao
) : JokeRepository {

    override fun createJoke() {
        TODO("Not yet implemented")
    }

    override fun getAllJokes() {
        TODO("Not yet implemented")
    }
}