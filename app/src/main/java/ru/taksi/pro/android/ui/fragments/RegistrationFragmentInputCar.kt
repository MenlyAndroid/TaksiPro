package ru.taksi.pro.android.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.InputCarFragmentBinding
import ru.taksi.pro.android.mvvm.data.EventArgs
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.vm.InputCarViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RegistrationFragmentInputCar : Fragment() {
    private lateinit var binding: InputCarFragmentBinding
    private var isRegistration = true

    @Inject
    lateinit var viewModel: InputCarViewModel

    companion object {
        private const val IS_REGISTRATION = "is registration"
        fun newInstance(isRegistration: Boolean) = RegistrationFragmentInputCar().apply {
            arguments = Bundle().apply {
                putBoolean(IS_REGISTRATION, isRegistration)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            isRegistration = it.getBoolean(IS_REGISTRATION, true)
        }
        TaxiProApplication.component.inject(this)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.input_car_fragment,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onContentChanged()
        setComponentsValue()
        initComponents()
        initViewModule()
    }

    private fun initViewModule() {
        requireActivity().let {
            viewModel.getAnswerLiveData().observe(it, { value ->
                when (value.event) {
                    EventArgs.DONE -> {
                        viewModel.onCleared()
                        it.supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ScreenMyDetailsFragment()).commit()
                    }
                    EventArgs.ERROR -> {
                        val toast =
                            Toast.makeText(requireActivity(), value.text, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    }
                }
            })
        }
    }

    private fun setComponentsValue() {
        UserProperties.instance.carBrand?.let {
            binding.carBrand.setText(it)
        }
        UserProperties.instance.carModel?.let {
            binding.carModel.setText(it)
        }
        UserProperties.instance.carYear?.let {
            binding.carYear.setText(it)
        }
        UserProperties.instance.carColor?.let {
            binding.carColor.setText(it)
        }
        UserProperties.instance.carNumber?.let {
            binding.carNumber.setText(it)
        }
        UserProperties.instance.carWIN?.let {
            binding.carWin.setText(it)
        }
        UserProperties.instance.carCertificate?.let {
            binding.carCertificate.setText(it)
        }
        UserProperties.instance.licenseNumber?.let {
            binding.licenseNumber.setText(it)
        }
    }

    private fun initComponents() {
        binding.carBrand.addTextChangedListener {
            binding.carBrandLayout.isErrorEnabled = false
            UserProperties.instance.carBrand = it.toString().trim()
        }
        binding.carModel.addTextChangedListener {
            binding.carModelLayout.isErrorEnabled = false
            UserProperties.instance.carModel = it.toString().trim()
        }
        binding.carYear.addTextChangedListener {
            binding.carYearLayout.isErrorEnabled = false
            UserProperties.instance.carYear = it.toString().trim()
        }
        binding.carColor.addTextChangedListener {
            binding.carColorLayout.isErrorEnabled = false
            UserProperties.instance.carColor = it.toString().trim()
        }
        binding.carNumber.addTextChangedListener {
            binding.carNumberLayout.isErrorEnabled = false
            UserProperties.instance.carNumber = it.toString().trim()
        }
        binding.carWin.addTextChangedListener {
            binding.carWinLayout.isErrorEnabled = false
            UserProperties.instance.carWIN = it.toString().trim()
        }

        binding.carCertificate.addTextChangedListener {
            binding.carCertificateLayout.isErrorEnabled = false
            UserProperties.instance.carCertificate = it.toString().trim()
        }
        binding.licenseNumber.addTextChangedListener {
            binding.licenseNumberLayout.isErrorEnabled = false
            UserProperties.instance.licenseNumber = it.toString().trim()
        }
        binding.btnNext.setOnClickListener {
            if (!isRegistration) {
                viewModel.sendRegistrationData()
            } else if (checkEmptyInputFields() && checkDates()) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegistrationFragmentCheckInputData()).commit()
            }
        }
    }

    private fun checkEmptyInputFields(): Boolean {
        var isNotEmptyInputFields = true
        binding.carBrand.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.carBrandLayout.error = getString(R.string.error_empty_field)
                binding.carBrandLayout.isErrorEnabled = true
            }
        }
        binding.carModel.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.carModelLayout.error = getString(R.string.error_empty_field)
                binding.carModelLayout.isErrorEnabled = true
            }
        }
        binding.carColor.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.carColorLayout.error = getString(R.string.error_empty_field)
                binding.carColorLayout.isErrorEnabled = true
            }
        }
        binding.carNumber.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.carNumberLayout.error = getString(R.string.error_empty_field)
                binding.carNumberLayout.isErrorEnabled = true
            }
        }
        binding.carWin.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.carWinLayout.error = getString(R.string.error_empty_field)
                binding.carWinLayout.isErrorEnabled = true
            }
        }
        binding.carCertificate.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.carCertificateLayout.error = getString(R.string.error_empty_field)
                binding.carCertificateLayout.isErrorEnabled = true
            }
        }
        binding.carYear.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.carYearLayout.error = getString(R.string.error_empty_field)
                binding.carYearLayout.isErrorEnabled = true
            }
        }
        binding.licenseNumber.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.licenseNumberLayout.error = getString(R.string.error_empty_field)
                binding.licenseNumberLayout.isErrorEnabled = true
            }
        }
        return isNotEmptyInputFields
    }

    @SuppressLint("SimpleDateFormat")
    private fun checkDates(): Boolean {
        var isCurrentDates = true
        val dateFormat = SimpleDateFormat("yyyy")
        val currentDate = Date()
        binding.carYear.text?.toString()?.let {
            try {
                val date: Date = dateFormat.parse(it)
                if (date > currentDate) {
                    isCurrentDates = false
                    binding.carYearLayout.error = getString(R.string.error_current_date)
                    binding.carYearLayout.isErrorEnabled = true
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                isCurrentDates = false
                binding.carYearLayout.error = getString(R.string.error_current_date)
                binding.carYearLayout.isErrorEnabled = true
            }
        }
        return isCurrentDates
    }
}