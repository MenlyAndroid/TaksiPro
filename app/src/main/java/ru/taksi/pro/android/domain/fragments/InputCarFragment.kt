package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.InputCarFragmentBinding
import ru.taksi.pro.android.domain.helpers.TextChangedHelper
import ru.taksi.pro.android.mvvm.data.UserProperties

class InputCarFragment : Fragment() {
    private var binding: InputCarFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.input_car_fragment,
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
            UserProperties.instance.carBrand?.let {
                bind.carBrand.setText(it)
            }
            UserProperties.instance.carModel?.let {
                bind.carModel.setText(it)
            }
            UserProperties.instance.carYear?.let {
                bind.carYear.setText(it)
            }
            UserProperties.instance.carColor?.let {
                bind.carColor.setText(it)
            }
            UserProperties.instance.carNumber?.let {
                bind.carNumber.setText(it)
            }
            UserProperties.instance.carWIN?.let {
                bind.carWin.setText(it)
            }
            UserProperties.instance.carCertificate?.let {
                bind.carCertificate.setText(it)
            }
            UserProperties.instance.licenseNumber?.let {
                bind.licenseNumber.setText(it)
            }

        }
    }

    private fun initComponents() {
        binding?.carCertificate?.let {
            it.addTextChangedListener(
                TextChangedHelper.getSeriaEndNumberTextWatcher(
                    it,
                    UserProperties.CAR_CERTIFICATE
                )
            )
        }
        binding?.carBrand?.addTextChangedListener {
            UserProperties.instance.carBrand = it.toString()
        }
        binding?.carModel?.addTextChangedListener {
            UserProperties.instance.carModel = it.toString()
        }
        binding?.carYear?.addTextChangedListener {
            UserProperties.instance.carYear = it.toString()
        }
        binding?.carColor?.addTextChangedListener {
            UserProperties.instance.carColor = it.toString()
        }
        binding?.carNumber?.addTextChangedListener {
            UserProperties.instance.carNumber = it.toString()
        }
        binding?.carWin?.addTextChangedListener {
            UserProperties.instance.carWIN = it.toString()
        }
        binding?.licenseNumber?.addTextChangedListener {
            UserProperties.instance.licenseNumber = it.toString()
        }


//        binding?.btnNext?.setOnClickListener {
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.container, InputDriverDataFragment()).commit()
//        }
    }
}