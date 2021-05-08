package ru.taksi.pro.android.domain.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrMainBinding
import ru.taksi.pro.android.domain.fragments.*
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

    override fun onContentChanged() {
        super.onContentChanged()
        val myFragment = supportFragmentManager.findFragmentById(R.id.container)
        Log.d("!!!", "onContentChanged: ${myFragment?.id}")
        if (myFragment != null && myFragment.isVisible) {
            Log.d("!!!", "onContentChanged: ${myFragment.id}")

            when (myFragment) {
                is ChoiceTariffFragment -> {
                    binding.stepView.visibility = View.VISIBLE
                    binding.currentStepTv.text = "1"
                }
                is  ChoiceAggregatorFragment -> {
                    binding.stepView.visibility = View.VISIBLE
                    binding.currentStepTv.text = "2"
                }
                is  InputPassportDataFragment -> {
                    binding.stepView.visibility = View.VISIBLE
                    binding.currentStepTv.text = "3"
                }
                is  InputDriverDataFragment -> {
                    binding.stepView.visibility = View.VISIBLE
                    binding.currentStepTv.text = "4"
                }
                is  InputCarFragment -> {
                    binding.stepView.visibility = View.VISIBLE
                    binding.currentStepTv.text = "5"
                }
                is CheckInputDataFragment -> {
                    binding.stepView.visibility = View.VISIBLE
                }
                is  RegistrationFragmentAddPhoto -> {
                    binding.stepView.visibility = View.VISIBLE
                    binding.currentStepTv.text = "6"
                }
                is  RegistrationFragmentSendPhotos -> {
                    binding.stepView.visibility = View.VISIBLE
                    binding.currentStepTv.text = "6"
                }
                else -> binding.stepView.visibility = View.GONE
            }
        }
    }
}