package com.example.fallingwords.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fallingwords.model.WordProperty
import com.example.fallingwords.repository.network.Constants
import com.example.fallingwords.repository.network.WordsApi
import com.example.fallingwords.util.getRandomNum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val TAG = "GameViewModel"

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _wordPairs = MutableLiveData<WordProperty>()
    val wordPairs : LiveData<WordProperty>
        get() = _wordPairs

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private var _retry = MutableLiveData<Int>()
    val retry : LiveData<Int>
        get() = _retry

    private var iscorrect = false
    private lateinit var totalquestion : MutableList<WordProperty>

    init {
        getWords()
    }

    private fun getWords() {
        coroutineScope.launch {
            Log.d(TAG, "getWords")

            val listWords = WordsApi.retrofitService.getWords()

            /**
             * Create substring from listWords
             * Size of substring is = total points to win + total lives - 1
             */
            val i = getRandomNum(listWords.size - (Constants.TOTAL_QUESTIONS + Constants.TOTAL_RETRY))
            totalquestion = listWords.subList(i, i + (Constants.TOTAL_QUESTIONS + Constants.TOTAL_RETRY - 1)) as MutableList<WordProperty>

            _score.value = 0
            _retry.value = Constants.TOTAL_RETRY

            nextWord()
        }
    }

    private fun nextWord(){
        Log.d(TAG, "nextWord")
        val i = getRandomNum(2)

        if (i.equals(0) || totalquestion.size == 1){
            Log.d(TAG, "Correct word pair")
            _wordPairs.value = totalquestion[totalquestion.size - 1]
            iscorrect = true

        } else {
            Log.d(TAG, "Wrong word pair")
            val wordProperty  = WordProperty(
                totalquestion[totalquestion.size - 1].lang_1_word ,
                totalquestion[getRandomNum(totalquestion.size - 2)].lang_2_word
            )
            _wordPairs.value = wordProperty
            iscorrect = false
        }
    }

    fun playerResponse(response : Boolean?){
        Log.d(TAG, "playerResponse : $response")

        if (response != null){
            if (response.equals(iscorrect)) {
                _score.value = _score.value?.plus(1)
            } else {
                _retry.value = _retry.value?.minus(1)
            }

        } else {
            _retry.value = _retry.value?.minus(1)
        }
        if (score.value == Constants.TOTAL_QUESTIONS || retry.value == 0){
            Log.d(TAG, "playerResponse : Game Finished")
            return
        } else {
            totalquestion.removeLast()
            nextWord()
        }
    }

    /**
     * @return string for retry score board
     */
    fun retryString() : String{
        var tempString = ""
        for(i in 1..retry.value!!) tempString = tempString + "$"
        return tempString
    }

    /**
     * @return string for players_score score board
     */
    fun scoreString() : String {
        return score.value.toString() + "/" + Constants.TOTAL_QUESTIONS.toString()
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("onCleared","onCleared")
        viewModelJob.cancel()
    }
}