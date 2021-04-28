package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.ChoiceTariffFragmentBinding
import ru.taksi.pro.android.mvvm.vm.ChoiceTariffViewModel
import javax.inject.Inject

class ChoiceTariffFragment : Fragment() {
    private val TARIFF_START = "start"
    private val TARIFF_COMFORT = "komfort"
    private val TARIFF_PREMIUM = "premium"
    private var binding: ChoiceTariffFragmentBinding? = null
    lateinit var bottomSheetViewStart: ConstraintLayout
    lateinit var bottomSheetViewComfort: ConstraintLayout
    lateinit var bottomSheetViewPremium: ConstraintLayout

    @Inject
    lateinit var viewModel: ChoiceTariffViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        TaxiProApplication.component.inject(this)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.choice_tariff_fragment, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetViewStart = binding!!.root.findViewById(R.id.layoutBottomSheetStart)
        bottomSheetViewComfort = binding!!.root.findViewById(R.id.layoutBottomSheetComfort)
        bottomSheetViewPremium = binding!!.root.findViewById(R.id.layoutBottomSheetPremium)
        viewModel.getTariff()?.let {
            when (it) {
                TARIFF_START -> {
                    binding?.checkBox?.isChecked = true
                    onButtonStartClickListener()
                }
                TARIFF_COMFORT -> {
                    binding?.checkBox2?.isChecked = true
                    onComfortClickListener()
                }
                TARIFF_PREMIUM -> {
                    binding?.checkBox3?.isChecked = true
                    onPremiumClickListener()
                }
            }
        }

        binding?.bottomSheet?.setOnClickListener { onButtonClickListener() }
        binding?.rollUp?.setOnClickListener { onButtonUpClickListener() }

        binding?.bottomSheetComfort?.setOnClickListener { onButtonComfortClickListener() }
        binding?.rollUp2?.setOnClickListener { onButtonUp2ClickListener() }

        binding?.bottomSheetPremium?.setOnClickListener { onButtonPremiumClickListener() }
        binding?.rollUp3?.setOnClickListener { onButtonUp3ClickListener() }

        binding?.checkBox?.setOnClickListener { onButtonStartClickListener() }
        binding?.checkBox2?.setOnClickListener { onComfortClickListener() }
        binding?.checkBox3?.setOnClickListener { onPremiumClickListener() }

        binding?.buttonChooseATariff?.setOnClickListener {
            viewModel.onCleared()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChoiceAggregatorFragment()).commit()
        }
    }

    private fun onButtonClickListener() {
        bottomSheetViewStart.visibility = View.VISIBLE
        binding?.bottomSheet?.visibility = View.GONE
        binding?.rollUp?.visibility = View.VISIBLE
    }

    private fun onButtonUpClickListener() {
        bottomSheetViewStart.visibility = View.GONE
        binding?.bottomSheet?.visibility = View.VISIBLE
        binding?.rollUp?.visibility = View.GONE
    }

    private fun onButtonComfortClickListener() {
        bottomSheetViewComfort.visibility = View.VISIBLE
        binding?.bottomSheetComfort?.visibility = View.GONE
        binding?.rollUp2?.visibility = View.VISIBLE
    }

    private fun onButtonUp2ClickListener() {
        bottomSheetViewComfort.visibility = View.GONE
        binding?.bottomSheetComfort?.visibility = View.VISIBLE
        binding?.rollUp2?.visibility = View.GONE
    }

    private fun onButtonPremiumClickListener() {
        bottomSheetViewPremium.visibility = View.VISIBLE
        binding?.bottomSheetPremium?.visibility = View.GONE
        binding?.rollUp3?.visibility = View.VISIBLE
    }

    private fun onButtonUp3ClickListener() {
        bottomSheetViewPremium.visibility = View.GONE
        binding?.bottomSheetPremium?.visibility = View.VISIBLE
        binding?.rollUp3?.visibility = View.GONE
    }

    var tariff = ""
    private fun onButtonStartClickListener() {
        tariff = "Выбрать тариф Старт"
        binding?.buttonChooseATariff?.text = tariff
        binding?.checkBox2?.isChecked = false
        binding?.checkBox3?.isChecked = false
        tariff = TARIFF_START
        viewModel.setTariff(tariff)
    }

    private fun onComfortClickListener() {
        tariff = "Выбрать тариф Комфорт"
        binding?.buttonChooseATariff?.text = tariff
        binding?.checkBox?.isChecked = false
        binding?.checkBox3?.isChecked = false
        tariff = TARIFF_COMFORT
        viewModel.setTariff(tariff)
    }

    private fun onPremiumClickListener() {
        tariff = "Выбрать тариф Премиум"
        binding?.buttonChooseATariff?.text = tariff
        binding?.checkBox?.isChecked = false
        binding?.checkBox2?.isChecked = false
        tariff = TARIFF_PREMIUM
        viewModel.setTariff(tariff)
    }
}