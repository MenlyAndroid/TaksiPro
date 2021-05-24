package ru.taksi.pro.android.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.InputDriverDataFragmentBinding
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.ui.helpers.TextChangedHelper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RegistrationFragmentInputDriverData : Fragment() {
    private var binding: InputDriverDataFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.input_driver_data_fragment,
                container,
                false
            )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onContentChanged()
        setComponentsValue()
        initComponents()
    }

    private fun setComponentsValue() {
        binding?.let { bind ->
            UserProperties.instance.driverNumbers?.let {
                bind.driverNumber.setText(it)
            }
            UserProperties.instance.driverIssued?.let {
                bind.dateDriverIssued.setText(it)
            }
            UserProperties.instance.driverIssuedTo?.let {
                bind.driverIssuedTo.setText(it)
            }
        }
    }

    private fun initComponents() {
        binding?.dateDriverIssued?.let {
            it.addTextChangedListener {
                binding?.dateDriverIssuedLayout?.isErrorEnabled = false
            }
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DRIVER_ISSUED
                )
            )
        }
        binding?.driverIssuedTo?.let {
            it.addTextChangedListener {
                binding?.driverIssuedToLayout?.isErrorEnabled = false
            }
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DRIVER_ISSUED_TO
                )
            )
        }
        binding?.driverNumber?.let {
            it.addTextChangedListener {
                binding?.driverNumberLayout?.isErrorEnabled = false
            }
            it.addTextChangedListener(
                TextChangedHelper.getSerialEndNumberTextWatcher(
                    it,
                    UserProperties.DRIVER_DATA
                )
            )
        }
        binding?.btnNext?.setOnClickListener {
            if (checkEmptyInputFields() && checkDates()) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegistrationFragmentInputCar()).commit()
            }
        }
    }

    private fun checkEmptyInputFields(): Boolean {
        var isNotEmptyInputFields = true
        binding?.let {
            if (it.driverNumber.text.toString() == "") {
                isNotEmptyInputFields = false
                it.driverNumberLayout.error = getString(R.string.error_empty_field)
                it.driverNumberLayout.isErrorEnabled = true
            }
        }
        binding?.let {
            if (it.dateDriverIssued.text.toString() == "") {
                isNotEmptyInputFields = false
                it.dateDriverIssuedLayout.error = getString(R.string.error_empty_field)
                it.dateDriverIssuedLayout.isErrorEnabled = true
            }
        }
        binding?.let {
            if (it.driverIssuedTo.text.toString() == "") {
                isNotEmptyInputFields = false
                it.driverIssuedToLayout.error = getString(R.string.error_empty_field)
                it.driverIssuedToLayout.isErrorEnabled = true
            }
        }
        return isNotEmptyInputFields
    }

    @SuppressLint("SimpleDateFormat")
    private fun checkDates(): Boolean {
        var isCurrentDates = true
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = Date()
        binding?.dateDriverIssued?.text?.toString()?.let {
            try {
                val date: Date = dateFormat.parse(it)
                if (date > currentDate) {
                    isCurrentDates = false
                    binding?.dateDriverIssuedLayout?.error = getString(R.string.error_current_date)
                    binding?.dateDriverIssuedLayout?.isErrorEnabled = true
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                isCurrentDates = false
                binding?.dateDriverIssuedLayout?.error = getString(R.string.error_current_date)
                binding?.dateDriverIssuedLayout?.isErrorEnabled = true
            }
        }
        binding?.driverIssuedTo?.text?.toString()?.let {
            try {
                val date: Date = dateFormat.parse(it)
                if (date > currentDate) {
                    isCurrentDates = false
                    binding?.driverIssuedToLayout?.error = getString(R.string.error_current_date)
                    binding?.driverIssuedToLayout?.isErrorEnabled = true
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                isCurrentDates = false
                binding?.driverIssuedToLayout?.error = getString(R.string.error_current_date)
                binding?.driverIssuedToLayout?.isErrorEnabled = true
            }
        }
        return isCurrentDates
    }
}