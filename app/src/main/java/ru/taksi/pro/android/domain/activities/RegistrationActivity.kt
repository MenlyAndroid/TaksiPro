package ru.taksi.pro.android.domain.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrMainBinding
import ru.taksi.pro.android.domain.fragments.RegistrationFragment

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: RegistrMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.registr_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, RegistrationFragment())
                .commit()
        }
    }
}