package dev.dojo.jokesflow.data.model

import com.google.gson.annotations.SerializedName

class JokeResponse(
    @SerializedName("value")
    val value: String
)