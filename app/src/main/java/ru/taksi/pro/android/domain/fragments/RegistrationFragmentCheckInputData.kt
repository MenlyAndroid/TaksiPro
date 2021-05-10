package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.CheckInputDataFragmentBinding
import ru.taksi.pro.android.domain.adapters.RVAdapterCheckInputData
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.helpers.TextFormatHelper
import ru.taksi.pro.android.mvvm.vm.CheckInputDataViewModel
import javax.inject.Inject


class RegistrationFragmentCheckInputData : Fragment() {
    lateinit var binding: CheckInputDataFragmentBinding

    @Inject
    lateinit var viewModel: CheckInputDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        TaxiProApplication.component.inject(this)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.check_input_data_fragment,
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
        initRV()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getAnswerLiveData().observe(requireActivity(), { value ->
            if (value == "true") {
                viewModel.onCleared()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegistrationFragmentAddPhoto()).commit()
            } else {
                val toast = Toast.makeText(requireActivity(), value, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        })
    }

    private fun initComponents() {
        binding.changePhoneNumber.setOnClickListener {
            UserProperties.instance.token = null
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragment()).commit()
        }
        binding.changeTariff.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistratonFragmentChoiceTariff()).commit()
        }
        binding.changeAggregators.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentChoiceAggregator()).commit()
        }
        binding.changePassportDates.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentInputPassportData()).commit()
        }
        binding.changeDataOfCar.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentInputCar()).commit()
        }
        binding.changeDataOfDriver.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentInputDriverData()).commit()
        }
        binding.btnNext.setOnClickListener {
            viewModel.sendRegistrationData()
        }
    }

    private fun initRV() {
        binding.rvAggregators.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = RVAdapterCheckInputData(requireContext())
        binding.rvAggregators.adapter = adapter
        val listStrings = mutableListOf<String>()
        val listInts = mutableListOf<Int>()
        listStrings.addAll(UserProperties.instance.aggregators)
        for (item in listStrings) {
            when (item) {
                UserProperties.YANDEX_TAXI -> listInts.add(R.mipmap.yndex_taxi)
                UserProperties.UBER -> listInts.add(R.mipmap.uber)
                UserProperties.GETT -> listInts.add(R.mipmap.gett)
                UserProperties.CITY_MOBILE -> listInts.add(R.mipmap.citymobile)
            }
        }
        adapter.setData(listInts)
    }

    private fun setComponentsValue() {
        UserProperties.instance.phone?.let {
            binding.phoneNumber.text = it
        }
        UserProperties.instance.tariff?.let {
            binding.tariff.text = it
        }
        UserProperties.instance.surname?.let {
            binding.surname.text = it
        }
        UserProperties.instance.name?.let {
            binding.name.text = it
        }
        UserProperties.instance.patronymic?.let {
            binding.patronymic.text = it
        }
        UserProperties.instance.dateOfBird?.let {
            binding.dataOfBirth.text = TextFormatHelper.dateFormat(it)
        }
        UserProperties.instance.passportData?.let {
            binding.seriaAndNumberOfPassport.text = TextFormatHelper.serialEndNumberFormat(it)
        }
        UserProperties.instance.whoIssued?.let {
            binding.whoIssued.text = it
        }
        UserProperties.instance.dataOfIssued?.let {
            binding.whenIssued.text = TextFormatHelper.dateFormat(it)
        }
        UserProperties.instance.let {
            val address = TextFormatHelper.createStringAddress(
                it.city, it.district, it.street, it.home, it.apartments
            )
            binding.addressOfRegistration.text = address
        }
        UserProperties.instance.driverNumbers?.let {
            binding.driverNumbers.text = TextFormatHelper.serialEndNumberFormat(it)
        }
        UserProperties.instance.driverIssued?.let {
            binding.whenDriverIssued.text = TextFormatHelper.dateFormat(it)
        }
        UserProperties.instance.driverIssuedTo?.let {
            binding.driverIssuedTo.text = TextFormatHelper.dateFormat(it)
        }
        UserProperties.instance.carBrand?.let {
            binding.carBrand.text = it
        }
        UserProperties.instance.carModel?.let {
            binding.carModel.text = it
        }
        UserProperties.instance.carYear?.let {
            binding.carYear.text = it
        }
        UserProperties.instance.carColor?.let {
            binding.carColor.text = it
        }
        UserProperties.instance.carNumber?.let {
            binding.carNumber.text = it
        }
        UserProperties.instance.carWIN?.let {
            binding.carWin.text = it
        }
        UserProperties.instance.carCertificate?.let {
            binding.carLicense.text = TextFormatHelper.serialEndNumberFormat(it)
        }
        UserProperties.instance.licenseNumber?.let {
            binding.licenseNumber.text = it
        }
    }
}