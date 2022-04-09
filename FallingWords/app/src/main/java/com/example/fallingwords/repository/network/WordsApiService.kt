package com.example.fallingwords.repository.network

import com.example.fallingwords.model.WordProperty
import com.example.fallingwords.repository.network.Constants.GAME_API
import retrofit2.http.GET

interface WordsApiService {
    @GET(GAME_API)
    suspend fun getWords() : List<WordProperty>
}