package dev.dojo.jokesflow.di

import androidx.room.Room
import dev.dojo.jokesflow.HttpClient
import dev.dojo.jokesflow.createHttpClient
import dev.dojo.jokesflow.data.remote.JokeService
import dev.dojo.jokesflow.data.local.JokeDatabase
import dev.dojo.jokesflow.data.repository.JokeRepositoryImpl
import dev.dojo.jokesflow.domain.repository.JokeRepository
import dev.dojo.jokesflow.domain.usecase.GetNewJokeUseCase
import dev.dojo.jokesflow.domain.usecase.ListJokesUseCase
import dev.dojo.jokesflow.domain.usecase.listJokesUseCase
import dev.dojo.jokesflow.presentation.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { Room.databaseBuilder(get(), JokeDatabase::class.java, "jokes.db").build() }
    single { createHttpClient() }
}

val jokeModule = module {
    factory { get<JokeDatabase>().getJokeDao() }
    factory { get<HttpClient>().create(JokeService::class) }
    factory<JokeRepository> { JokeRepositoryImpl(jokeService = get(), jokeDao = get()) }
    factory { GetNewJokeUseCase(repository = get()) }
    viewModel { JokeViewModel(getNewJokeUseCase = get(), listJokesUseCase = { listJokesUseCase(get()) }) }
}