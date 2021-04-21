package ru.taksi.pro.android.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrMainBinding
import ru.taksi.pro.android.ui.fragments.RegistrFragmentWelcome

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: RegistrMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrMainBinding.inflate(layoutInflater)
        val view = binding.root
        spannableTaxi(getString(R.string.taxi_pro))
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
  //          .add(R.id.container, RegistrFragment())
 //           .add(R.id.container,RegistrFragmentEnterCode())
            .add(R.id.container, RegistrFragmentWelcome())
            .commit()
    }

    fun spannableTaxi(word: String) {
        val spannableTaxi = SpannableStringBuilder(word)
        spannableTaxi.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            0, // start
            1, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.headerTitle.text = spannableTaxi
    }
}