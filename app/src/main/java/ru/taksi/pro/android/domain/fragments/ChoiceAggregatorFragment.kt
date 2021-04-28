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
import ru.taksi.pro.android.mvvm.vm.ChoiceAggregatorViewModel
import javax.inject.Inject


class ChoiceAggregatorFragment : Fragment() {
    private var binding: ChoiceAggregatorFragmentBinding? = null
    private val GETT = "gett"
    private val UBER = "uber"
    private val CITY_MOBILE = "city mobile"
    private val YANDEX_TAXI = "yandex taxi"

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
                GETT -> binding?.checkboxGett?.isChecked = true
                UBER -> binding?.checkboxUber?.isChecked = true
                CITY_MOBILE -> binding?.checkboxCityMobile?.isChecked = true
                YANDEX_TAXI -> binding?.checkboxYandexTaxi?.isChecked = true
            }
        }

        binding?.checkboxGett?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(GETT)
            } else {
                onUncheckBox(GETT)
            }
        }
        binding?.checkboxUber?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(UBER)
            } else {
                onUncheckBox(UBER)
            }
        }
        binding?.checkboxYandexTaxi?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(YANDEX_TAXI)
            } else {
                onUncheckBox(YANDEX_TAXI)
            }
        }
        binding?.checkboxCityMobile?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onCheckBox(CITY_MOBILE)
            } else {
                onUncheckBox(CITY_MOBILE)
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