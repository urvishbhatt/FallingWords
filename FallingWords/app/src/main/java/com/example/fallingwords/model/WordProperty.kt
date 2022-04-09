package com.example.fallingwords.model

import com.squareup.moshi.Json

data class WordProperty(
    @Json(name = "text_eng") val lang_1_word : String,
    @Json(name = "text_spa") val lang_2_word : String,
)