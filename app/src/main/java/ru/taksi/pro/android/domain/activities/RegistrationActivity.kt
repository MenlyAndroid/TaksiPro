package ru.taksi.pro.android.domain.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrMainBinding
import ru.taksi.pro.android.domain.fragments.*
import ru.taksi.pro.android.domain.helpers.TextChangedHelper
import ru.taksi.pro.android.mvvm.data.UserProperties

class RegistrationActivity : AppCompatActivity() {
    companion object {
        val READ_WRITE_STORAGE = 1100
    }

    private var binding: RegistrMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.registr_main)
        if (UserProperties.instance.token == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, RegistrationFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, RegistratonFragmentChoiceTariff())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        onContentChanged()
    }

    override fun onContentChanged() {
        super.onContentChanged()
        val myFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (myFragment != null && myFragment.isVisible) {
            binding?.let { bind ->
                when (myFragment) {
                    is RegistratonFragmentChoiceTariff -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("1", this)
                    }
                    is RegistrationFragmentChoiceAggregator -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("2", this)
                    }
                    is RegistrationFragmentInputPassportData -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("3", this)
                    }
                    is RegistrationFragmentInputDriverData -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("4", this)
                    }
                    is RegistrationFragmentInputCar -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("5", this)
                    }
                    is RegistrationFragmentCheckInputData -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("0", this)
                    }
                    is RegistrationFragmentAddPhoto -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("6", this)
                    }
                    is RegistrationFragmentSendPhotos -> {
                        bind.stepView.visibility = View.VISIBLE
                        bind.stepTv.text = TextChangedHelper.stepStringBuilder("6", this)
                    }
                    else -> bind.stepView.visibility = View.GONE
                }
            }
        } else binding?.stepView?.visibility = View.GONE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_WRITE_STORAGE) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentSendPhotos(null)).commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.putExtra("photo", requestCode)
        if (resultCode == RESULT_OK) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentSendPhotos(data)).commit()

        }
    }
}