package dev.dojo.jokesflow.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)
