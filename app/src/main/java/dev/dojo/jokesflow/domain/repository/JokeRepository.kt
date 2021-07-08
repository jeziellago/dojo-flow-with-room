package dev.dojo.jokesflow.domain.repository

interface JokeRepository {
    fun createJoke()
    fun getAllJokes()
}