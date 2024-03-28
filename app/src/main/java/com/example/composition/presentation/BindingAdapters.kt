package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

@BindingAdapter("finishEmoji")
fun bindCurrentEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

@BindingAdapter("requiredAnswer")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswer")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswer(gameResult)
    )
}

private fun getPercentOfRightAnswer(gameResult: GameResult) = with(gameResult) {
    if (countOfRightAnswer == 0) {
        0
    } else {
        ((countOfRightAnswer / countOfQuestion.toDouble()) * 100).toInt()
    }
}
@BindingAdapter("colorAnswersProgress")
fun bindColorAnswersProgress(textView: TextView, enoughCount: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enoughCount))
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("settingProgress")
fun bindStateProgress(progressBar: ProgressBar, percentOfRightAnswer: Int) {
    progressBar.setProgress(percentOfRightAnswer, true)
}

@BindingAdapter("settingSecondaryProgress")
fun bindStateSecondaryProgress(progressBar: ProgressBar, minPercentOfRightAnswer: Int) {
    progressBar.secondaryProgress = minPercentOfRightAnswer
}

@BindingAdapter("settingColorProgressBar")
fun bindColorProgressBar(progressBar: ProgressBar,enoughCount: Boolean){
    val color = getColorByState(progressBar.context,enoughCount)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}
@BindingAdapter("setNumberAsText")
fun bindNumberAsText (textView: TextView,number: Int){
    textView.text = number.toString()
}
@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView,clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

interface OnOptionClickListener{
    fun onOptionClick(option : Int)
}
