package ru.taksi.pro.android.ui.activities

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrMainBinding
import ru.taksi.pro.android.ui.fragments.RegistrFragment

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: RegistrMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.registr_main)
        spannableTaxi(getString(R.string.taxi_pro))
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, RegistrFragment())
 //           .add(R.id.container, RegistrFragmentEnterCode())
 //           .add(R.id.container, RegistrFragmentWelcome())
            .commit()
    }
    fun spannableTaxi(word: String) {
        val spannableTaxi = SpannableStringBuilder(word)
        spannableTaxi.setSpan(
            ForegroundColorSpan(Color.rgb(251,187,5)),
                0, // start
                1, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE,
            )
        spannableTaxi.setSpan(AbsoluteSizeSpan(60), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(AbsoluteSizeSpan(60), 6,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(StyleSpan(Typeface.BOLD), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.headerTitle.text = spannableTaxi
    }
}