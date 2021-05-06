package ru.taksi.pro.android.domain.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrMainBinding
import ru.taksi.pro.android.domain.fragments.ChoiceAggregatorFragment
import ru.taksi.pro.android.domain.fragments.ChoiceTariffFragment
import ru.taksi.pro.android.domain.fragments.RegistrationFragment
import ru.taksi.pro.android.mvvm.data.UserProperties

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: RegistrMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.registr_main)
        if (UserProperties.instance.token == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, RegistrationFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ChoiceTariffFragment())
                .commit()
        }
    }
}