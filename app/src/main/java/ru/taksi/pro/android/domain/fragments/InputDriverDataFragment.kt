package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.InputDriverDataFragmentBinding
import ru.taksi.pro.android.domain.helpers.TextChangedHelper
import ru.taksi.pro.android.mvvm.data.UserProperties

class InputDriverDataFragment : Fragment() {
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
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DRIVER_ISSUED
                )
            )
        }
        binding?.driverIssuedTo?.let {
            it.addTextChangedListener(
                TextChangedHelper.getDateTextWatcher(
                    it,
                    UserProperties.DRIVER_ISSUED_TO
                )
            )
        }
        binding?.driverNumber?.let {
            it.addTextChangedListener(
                TextChangedHelper.getSeriaEndNumberTextWatcher(
                    it,
                    UserProperties.DRIVER_DATA
                )
            )
        }
        binding?.btnNext?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, InputCarFragment()).commit()
        }
    }
}