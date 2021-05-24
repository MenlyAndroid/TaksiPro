package ru.taksi.pro.android.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.InputPassprtDataFragmentBinding
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.vm.InputPassportDataViewModel
import ru.taksi.pro.android.ui.adapters.RVAdapterAddress
import ru.taksi.pro.android.ui.helpers.TextChangedHelper
import ru.taksi.pro.android.ui.view.IAddressChanger
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class RegistrationFragmentInputPassportData : IAddressChanger, Fragment() {
    private lateinit var binding: InputPassprtDataFragmentBinding
    private var adapter: RVAdapterAddress? = null
    private var isChanger = true

    @Inject
    lateinit var viewModel: InputPassportDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        TaxiProApplication.component.inject(this)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.input_passprt_data_fragment,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onContentChanged()
        initRV()
        setComponentsValue()
        initComponents()
        initViewModule()
    }

    private fun initRV() {
        adapter = RVAdapterAddress(requireContext(), this)
        binding.rvAddresses.layoutManager =
            LinearLayoutManager(requireContext())
        binding.rvAddresses.adapter = adapter
    }

    private fun initViewModule() {
        requireActivity().let {
            viewModel.getAnswerLiveData().observe(it, { value ->
                adapter?.setData(value as MutableList<String>)
            })
        }
    }

    private fun setComponentsValue() {
        UserProperties.instance.name?.let {
            binding.inputName.setText(it)
        }
        UserProperties.instance.surname?.let {
            binding.inputSurname.setText(it)
        }
        UserProperties.instance.patronymic?.let {
            binding.inputPatronymic.setText(it)
        }
        UserProperties.instance.dateOfBird?.let {
            binding.inputDateOfBirth.setText(it)
        }
        UserProperties.instance.passportData?.let {
            binding.inputPassportData.setText(it)
        }
        UserProperties.instance.whoIssued?.let {
            binding.inputIssued.setText(it)
        }
        UserProperties.instance.dataOfIssued?.let {
            binding.inputDateOfIssued.setText(it)
        }
        UserProperties.instance.address?.let {
            binding.inputAddress.setText(it)
        }
    }

    private fun initComponents() {
        binding.inputDateOfBirth.let {
            it.addTextChangedListener {
                binding.inputDateOfBirthLayout.isErrorEnabled = false
            }
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DATE_OF_BIRD
                )
            )
        }
        binding.inputDateOfIssued.let {
            it.addTextChangedListener {
                binding.inputDateOfIssuedLayout.isErrorEnabled = false
            }
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DATE_OF_ISSUED
                )
            )
        }
        binding.inputPassportData.let {
            it.addTextChangedListener {
                binding.inputPassportDataLayout.isErrorEnabled = false
            }
            it.addTextChangedListener(
                TextChangedHelper.getSerialEndNumberTextWatcher(
                    it,
                    UserProperties.PASSPORT_DATA
                )
            )
        }
        binding.inputSurname.addTextChangedListener {
            binding.inputSurnameLayout.isErrorEnabled = false
            UserProperties.instance.surname = it.toString().trim()
        }
        binding.inputName.addTextChangedListener {
            binding.inputNameLayout.isErrorEnabled = false
            UserProperties.instance.name = it.toString().trim()
        }
        binding.inputPatronymic.addTextChangedListener {
            binding.inputPatronymicLayout.isErrorEnabled = false
            UserProperties.instance.patronymic = it.toString().trim()
        }
        binding.inputIssued.addTextChangedListener {
            binding.inputIssuedLayout.isErrorEnabled = false
            UserProperties.instance.whoIssued = it.toString().trim()
        }
        binding.inputAddress.addTextChangedListener {
            binding.inputAddressLayout.isErrorEnabled = false
            UserProperties.instance.address = it.toString().trim()
            if (isChanger) {
                viewModel.getVariants(it.toString().trim())
            }
            isChanger = true
        }
        binding.btnNext.setOnClickListener {
            if (checkEmptyInputFields() && checkDates()) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegistrationFragmentInputDriverData()).commit()
            }
        }
    }

    private fun checkEmptyInputFields(): Boolean {
        var isNotEmptyInputFields = true
        binding.inputName.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputNameLayout.error = getString(R.string.error_empty_field)
                binding.inputNameLayout.isErrorEnabled = true
            }
        }
        binding.inputSurname.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputSurnameLayout.error = getString(R.string.error_empty_field)
                binding.inputSurnameLayout.isErrorEnabled = true
            }
        }
        binding.inputPatronymic.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputPatronymicLayout.error = getString(R.string.error_empty_field)
                binding.inputPatronymicLayout.isErrorEnabled = true
            }
        }
        binding.inputDateOfBirth.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputDateOfBirthLayout.error = getString(R.string.error_empty_field)
                binding.inputDateOfBirthLayout.isErrorEnabled = true
            }
        }
        binding.inputPassportData.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputPassportDataLayout.error = getString(R.string.error_empty_field)
                binding.inputPassportDataLayout.isErrorEnabled = true
            }
        }
        binding.inputIssued.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputIssuedLayout.error = getString(R.string.error_empty_field)
                binding.inputIssuedLayout.isErrorEnabled = true
            }
        }
        binding.inputDateOfIssued.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputDateOfIssuedLayout.error = getString(R.string.error_empty_field)
                binding.inputDateOfIssuedLayout.isErrorEnabled = true
            }
        }
        binding.inputAddress.let {
            if (it.text.toString() == "") {
                isNotEmptyInputFields = false
                binding.inputAddressLayout.error = getString(R.string.error_empty_field)
                binding.inputAddressLayout.isErrorEnabled = true
            }
        }
        return isNotEmptyInputFields
    }

    @SuppressLint("SimpleDateFormat")
    private fun checkDates(): Boolean {
        var isCurrentDates = true
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = Date()
        binding.inputDateOfBirth.text?.toString()?.let {
            try {
                val date: Date = dateFormat.parse(it)
                if (date > currentDate) {
                    isCurrentDates = false
                    binding.inputDateOfBirthLayout.error = getString(R.string.error_current_date)
                    binding.inputDateOfBirthLayout.isErrorEnabled = true
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                isCurrentDates = false
                binding.inputDateOfBirthLayout.error = getString(R.string.error_current_date)
                binding.inputDateOfBirthLayout.isErrorEnabled = true
            }
        }
        binding.inputDateOfIssued.text?.toString()?.let {
            try {
                val date: Date = dateFormat.parse(it)
                if (date > currentDate) {
                    isCurrentDates = false
                    binding.inputDateOfIssuedLayout.error = getString(R.string.error_current_date)
                    binding.inputDateOfIssuedLayout.isErrorEnabled = true
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                isCurrentDates = false
                binding.inputDateOfIssuedLayout.error = getString(R.string.error_current_date)
                binding.inputDateOfIssuedLayout.isErrorEnabled = true
            }
        }
        return isCurrentDates
    }

    override fun changeAddress(address: String) {
        adapter?.setData(mutableListOf())
        isChanger = false
        binding.inputAddress.setText(address)
    }
}