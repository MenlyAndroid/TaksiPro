package ru.taksi.pro.android.domain.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ActivityMainBinding
import ru.taksi.pro.android.domain.helpers.SpannableHelper


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.headerTitle.text = SpannableHelper.spannableTaxi(getString(R.string.taxi_pro))

        binding.button3.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

}