package dev.dojo.jokesflow.data.repository

import dev.dojo.jokesflow.data.local.Joke as JokeEntity
import dev.dojo.jokesflow.data.remote.JokeService
import dev.dojo.jokesflow.data.local.JokeDao
import dev.dojo.jokesflow.domain.Joke
import dev.dojo.jokesflow.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class JokeRepositoryImpl(
    private val jokeService: JokeService,
    private val jokeDao: JokeDao
) : JokeRepository {

    override suspend fun createJoke() {
        runCatching {
            jokeService.getJoke()
        }.onSuccess { jokeResponse ->
            jokeDao.insert(JokeEntity(name = jokeResponse.value))
        }
    }

    override fun getAllJokes(): Flow<List<Joke>> {
        return jokeDao.getAll()
            .map {
                it.map { jokeEntity ->
                    Joke(
                        value = jokeEntity.name
                    )
                }
            }
    }
}