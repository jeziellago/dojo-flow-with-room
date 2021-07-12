package dev.dojo.jokesflow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {
    @Query("SELECT * FROM joke")
    fun getAll(): Flow<List<Joke>>

    @Insert(onConflict = REPLACE)
    fun insert(joke: Joke)
}