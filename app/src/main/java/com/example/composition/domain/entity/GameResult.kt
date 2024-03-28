package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameResult (
    val winner : Boolean,
    val countOfRightAnswer : Int ,
    val countOfQuestion : Int ,
    val gameSettings : GameSettings
) : Parcelable{
    val scorePercentage : Int
        get() = ((countOfRightAnswer/countOfQuestion.toDouble()) * 100).toInt()
}