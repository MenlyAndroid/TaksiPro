package ru.taksi.pro.android.ui.fragments

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
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DATE_OF_BIRD
                )
            )
        }
        binding.inputDateOfIssued.let {
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DATE_OF_ISSUED
                )
            )
        }
        binding.inputPassportData.let {
            it.addTextChangedListener(
                TextChangedHelper.getSerialEndNumberTextWatcher(
                    it,
                    UserProperties.PASSPORT_DATA
                )
            )
        }
        binding.inputSurname.addTextChangedListener {
            UserProperties.instance.surname = it.toString()
        }
        binding.inputName.addTextChangedListener {
            UserProperties.instance.name = it.toString()
        }
        binding.inputPatronymic.addTextChangedListener {
            UserProperties.instance.patronymic = it.toString()
        }
        binding.inputIssued.addTextChangedListener {
            UserProperties.instance.whoIssued = it.toString()
        }
        binding.inputAddress.addTextChangedListener {
            UserProperties.instance.address = it.toString()
            if (isChanger) {
                viewModel.getVariants(it.toString())
            }
            isChanger = true
        }
        binding.btnNext.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentInputDriverData()).commit()
        }
    }

    override fun changeAddress(address: String) {
        adapter?.setData(mutableListOf())
        isChanger = false
        binding.inputAddress.setText(address)
    }
}