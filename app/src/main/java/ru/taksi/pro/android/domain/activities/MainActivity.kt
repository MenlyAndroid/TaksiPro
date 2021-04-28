package ru.taksi.pro.android.domain.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button3.setOnClickListener { onButtonsClickListener() }
        binding.button4.setOnClickListener { onButtonsClickListener() }
        binding.button5.setOnClickListener { onButtonsClickListener() }
    }

    private fun onButtonsClickListener() {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }
}
