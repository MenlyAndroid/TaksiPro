package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.InputPassprtDataFragmentBinding
import ru.taksi.pro.android.domain.helpers.TextChangedHelper
import ru.taksi.pro.android.mvvm.data.UserProperties

class InputPassportDataFragment : Fragment() {
    private var binding: InputPassprtDataFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.input_passprt_data_fragment,
                container,
                false
            )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setComponentsValue()
        initComponents()
    }

    private fun setComponentsValue() {
        binding?.let { bind ->
            UserProperties.instance.name?.let {
                bind.inputName.setText(it)
            }
            UserProperties.instance.surname?.let {
                bind.inputSurname.setText(it)
            }
            UserProperties.instance.patronymic?.let {
                bind.inputPatronymic.setText(it)
            }
            UserProperties.instance.dateOfBird?.let {
                bind.inputDateOfBirth.setText(it)
            }
            UserProperties.instance.passportData?.let {
                bind.inputPassportData.setText(it)
            }
            UserProperties.instance.whoIssued?.let {
                bind.inputIssued.setText(it)
            }
            UserProperties.instance.dataOfIssued?.let {
                bind.inputDateOfIssued.setText(it)
            }
            UserProperties.instance.city?.let {
                bind.inputCity.setText(it)
            }
            UserProperties.instance.district?.let {
                bind.inputDistrict.setText(it)
            }
            UserProperties.instance.street?.let {
                bind.inputStreet.setText(it)
            }
            UserProperties.instance.home?.let {
                bind.inputHome.setText(it)
            }
            UserProperties.instance.apartments?.let {
                bind.inputApartment.setText(it)
            }
        }
    }

    private fun initComponents() {
        binding?.inputDateOfBirth?.let {
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DATE_OF_BIRD
                )
            )
        }
        binding?.inputDateOfIssued?.let {
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DATE_OF_ISSUED
                )
            )
        }
        binding?.inputPassportData?.let {
            it.addTextChangedListener(
                TextChangedHelper.getSeriaEndNumberTextWatcher(
                    it,
                    UserProperties.PASSPORT_DATA
                )
            )
        }
        binding?.inputSurname?.addTextChangedListener {
            UserProperties.instance.surname = it.toString()
        }
        binding?.inputName?.addTextChangedListener {
            UserProperties.instance.name = it.toString()
        }
        binding?.inputPatronymic?.addTextChangedListener {
            UserProperties.instance.patronymic = it.toString()
        }
        binding?.inputIssued?.addTextChangedListener {
            UserProperties.instance.whoIssued = it.toString()
        }
        binding?.inputCity?.addTextChangedListener {
            UserProperties.instance.city = it.toString()
        }
        binding?.inputDistrict?.addTextChangedListener {
            UserProperties.instance.district = it.toString()
        }
        binding?.inputStreet?.addTextChangedListener {
            UserProperties.instance.street = it.toString()
        }
        binding?.inputHome?.addTextChangedListener {
            UserProperties.instance.home = it.toString()
        }
        binding?.inputApartment?.addTextChangedListener {
            UserProperties.instance.apartments = it.toString()
        }
        binding?.btnNext?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, InputDriverDataFragment()).commit()
        }
    }
}