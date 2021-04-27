package ru.taksi.pro.android.domain.helpers

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

object SpannableHelper {
    fun spannableTaxi(word: String): SpannableStringBuilder {
        val spannableTaxi = SpannableStringBuilder(word)
        spannableTaxi.setSpan(
            ForegroundColorSpan(Color.rgb(251,187,5)),
            0, // start
            1, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableTaxi.setSpan(AbsoluteSizeSpan(100), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(AbsoluteSizeSpan(100), 6,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(StyleSpan(Typeface.BOLD), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannableTaxi
    }
}