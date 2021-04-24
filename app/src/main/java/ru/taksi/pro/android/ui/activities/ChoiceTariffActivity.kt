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
import ru.taksi.pro.android.databinding.ChoiceTariffMainBinding
import ru.taksi.pro.android.ui.fragments.ChoiceTariffFragment

class ChoiceTariffActivity: AppCompatActivity() {
    lateinit var binding: ChoiceTariffMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.choice_tariff_main)
        val spannableTaxi = SpannableStringBuilder(getString(R.string.taxi_pro))
        spannableTaxi.setSpan(
            ForegroundColorSpan(Color.rgb(251,187,5)),
            0, // start
            1, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableTaxi.setSpan(AbsoluteSizeSpan(90), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(AbsoluteSizeSpan(90), 6,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTaxi.setSpan(StyleSpan(Typeface.BOLD), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.headerTitle.text = spannableTaxi
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
                      .add(R.id.choice_tariff_container, ChoiceTariffFragment())
            //          .add(R.id.choice_teriff_container,RegistrFragmentEnterCode())
       //     .add(R.id.choice_teriff_container, RegistrFragmentWelcome())
            .commit()
    }
}