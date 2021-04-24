package ru.taksi.pro.android.ui.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val spannableTaxi = SpannableStringBuilder("Такси Про")
        spannableTaxi.setSpan(
            ForegroundColorSpan(Color.rgb(251,187,5)),
            0, // start
            1, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableTaxi.setSpan(AbsoluteSizeSpan(60), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(AbsoluteSizeSpan(60), 6,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.headerTitle.text = spannableTaxi

        binding.button3.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

}
