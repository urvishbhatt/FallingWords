package com.example.fallingwords.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fallingwords.model.WordProperty
import com.example.fallingwords.repository.network.WordsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val TAG = "GameViewModel"

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var iscorrect = false
    private lateinit var totalquestion : MutableList<WordProperty>

    init {
        getWords()
    }

    private fun getWords() {
        coroutineScope.launch {
            Log.d(TAG, "getWords")

            val listWords = WordsApi.retrofitService.getWords()
            Log.d(TAG, listWords.size.toString())
        }
    }
}