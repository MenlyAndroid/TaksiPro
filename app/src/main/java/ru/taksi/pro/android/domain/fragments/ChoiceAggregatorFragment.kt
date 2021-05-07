package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.ChoiceAggregatorFragmentBinding
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.vm.ChoiceAggregatorViewModel
import javax.inject.Inject


class ChoiceAggregatorFragment : Fragment() {
    private var binding: ChoiceAggregatorFragmentBinding? = null

    @Inject
    lateinit var viewModel: ChoiceAggregatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        TaxiProApplication.component.inject(this)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.choice_aggregator_fragment, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAggregators()?.map {
            when (it) {
                UserProperties.GETT -> binding?.checkboxGett?.isChecked = true
                UserProperties.UBER -> binding?.checkboxUber?.isChecked = true
                UserProperties.CITY_MOBILE -> binding?.checkboxCityMobile?.isChecked = true
                UserProperties.YANDEX_TAXI -> binding?.checkboxYandexTaxi?.isChecked = true
            }
        }

        binding?.checkboxGett?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(UserProperties.GETT)
            } else {
                onUncheckBox(UserProperties.GETT)
            }
        }
        binding?.checkboxUber?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(UserProperties.UBER)
            } else {
                onUncheckBox(UserProperties.UBER)
            }
        }
        binding?.checkboxYandexTaxi?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(UserProperties.YANDEX_TAXI)
            } else {
                onUncheckBox(UserProperties.YANDEX_TAXI)
            }
        }
        binding?.checkboxCityMobile?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(UserProperties.CITY_MOBILE)
            } else {
                onUncheckBox(UserProperties.CITY_MOBILE)
            }
        }

        binding?.buttonChoose?.setOnClickListener {
            viewModel.onCleared()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, InputPassportDataFragment()).commit()
        }
    }

    private fun onCheckBox(aggregator: String) {
        viewModel.setAggregator(aggregator)
    }

    private fun onUncheckBox(aggregator: String) {
        viewModel.removeAggregator(aggregator)
    }
}