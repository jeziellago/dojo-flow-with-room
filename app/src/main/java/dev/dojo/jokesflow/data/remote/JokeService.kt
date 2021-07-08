package dev.dojo.jokesflow.data.remote

import dev.dojo.jokesflow.data.model.JokeResponse
import retrofit2.http.GET

interface JokeService {

    @GET("jokes/random")
    suspend fun getJoke(): JokeResponse
}