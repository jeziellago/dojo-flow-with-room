package dev.dojo.jokesflow.domain.usecase

import dev.dojo.jokesflow.domain.repository.JokeRepository

class GetNewJokeUseCase(private val repository: JokeRepository) {

    operator fun invoke() {

    }
}