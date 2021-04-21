package ru.taksi.pro.android.ui.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import ru.taksi.pro.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val spannableTaxi = SpannableStringBuilder("Такси Про")
        spannableTaxi.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            0, // start
            1, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.headerTitle.text = spannableTaxi

        binding.button3.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

}
