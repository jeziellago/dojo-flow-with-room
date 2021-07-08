package dev.dojo.jokesflow

import android.app.Application
import dev.dojo.jokesflow.di.applicationModule
import dev.dojo.jokesflow.di.jokeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JokesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JokesApplication)
            modules(applicationModule + jokeModule)
        }
    }
}