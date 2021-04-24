package ru.taksi.pro.android.domain.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ChoiceTariffMainBinding
import ru.taksi.pro.android.domain.fragments.ChoiceTariffFragment
import ru.taksi.pro.android.domain.helpers.SpannableHelper

class ChoiceTariffActivity: AppCompatActivity() {
    lateinit var binding: ChoiceTariffMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.choice_tariff_main)
        binding.headerTitle.text = SpannableHelper.spannableTaxi(getString(R.string.taxi_pro))
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .add(R.id.choice_tariff_container, ChoiceTariffFragment())
            .commit()
    }
}