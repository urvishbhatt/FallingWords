package com.example.fallingwords.util

import kotlin.random.Random


fun getRandomNum(until : Int) : Int {
    if (until.equals(0)){
        return 0
    } else {
        return Random.nextInt(0, until)
    }
}