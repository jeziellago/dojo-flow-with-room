package dev.dojo.jokesflow.domain.usecase

import dev.dojo.jokesflow.domain.repository.JokeRepository

class ListJokesUseCase(private val repository: JokeRepository) {

    operator fun invoke() {
        // get all jokes
    }

}