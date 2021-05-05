package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.CheckInputDataFragmentBinding
import ru.taksi.pro.android.domain.adapters.RVAdapterCheckInputData
import ru.taksi.pro.android.domain.helpers.TextChangedHelper
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.vm.ChoiceTariffViewModel
import javax.inject.Inject


class CheckInputDataFragment : Fragment() {
    lateinit var binding: CheckInputDataFragmentBinding

//    @Inject
//    lateinit var viewModel: ChoiceTariffViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
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
        setComponentsValue()
        initComponents()
        initRV()
    }

    private fun initComponents() {
        binding.changePhoneNumber.setOnClickListener {
            UserProperties.instance.token = null
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragment()).commit()
        }
        binding.changeTariff.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChoiceTariffFragment()).commit()
        }
        binding.changeAggregators.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChoiceAggregatorFragment()).commit()
        }
        binding.changePassportDates.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, InputPassportDataFragment()).commit()
        }
        binding.changeDataOfCar.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, InputCarFragment()).commit()
        }
        binding.changeDataOfDriver.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, InputDriverDataFragment()).commit()
        }
        binding.btnNext.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegistrationFragmentAddPhoto()).commit()
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
            binding.dataOfBirth.text = TextChangedHelper.dateFormat(it)
        }
        UserProperties.instance.passportData?.let {
            binding.seriaAndNumberOfPassport.text = TextChangedHelper.serialEndNumberFormat(it)
        }
        UserProperties.instance.whoIssued?.let {
            binding.whoIssued.text = it
        }
        UserProperties.instance.dataOfIssued?.let {
            binding.whenIssued.text = TextChangedHelper.dateFormat(it)
        }
        UserProperties.instance.let {
            val address =
                "${it.city}, ${it.district}, ул. ${it.street}, ${it.home}, ${it.apartments}"
            binding.addressOfRegistration.text = address
        }
        UserProperties.instance.driverNumbers?.let {
            binding.driverNumbers.text = TextChangedHelper.serialEndNumberFormat(it)
        }
        UserProperties.instance.driverIssued?.let {
            binding.whenDriverIssued.text = TextChangedHelper.dateFormat(it)
        }
        UserProperties.instance.driverIssuedTo?.let {
            binding.driverIssuedTo.text = TextChangedHelper.dateFormat(it)
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
            binding.carLicense.text = TextChangedHelper.serialEndNumberFormat(it)
        }
        UserProperties.instance.licenseNumber?.let {
            binding.licenseNumber.text = it
        }
    }
}