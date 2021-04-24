package ru.taksi.pro.android.domain.helpers

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan

object SpannableHelper {
    fun spannableTaxi(word: String): SpannableStringBuilder {
        val spannableTaxi = SpannableStringBuilder(word)
        spannableTaxi.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            0, // start
            1, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        return spannableTaxi
    }
}