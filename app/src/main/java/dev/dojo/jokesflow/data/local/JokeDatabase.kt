package dev.dojo.jokesflow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Joke::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun getJokeDao(): JokeDao
}