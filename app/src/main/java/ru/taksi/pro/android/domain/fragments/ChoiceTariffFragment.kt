package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ChoiceTariffFragmentBinding

class ChoiceTariffFragment: Fragment() {
    private var binding: ChoiceTariffFragmentBinding? = null
    lateinit var bottomSheetViewStart: ConstraintLayout
    lateinit var bottomSheetViewComfort: ConstraintLayout
    lateinit var bottomSheetViewPremium: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ChoiceTariffFragmentBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetViewStart = binding!!.root.findViewById(R.id.layoutBottomSheetStart)
        bottomSheetViewComfort = binding!!.root.findViewById(R.id.layoutBottomSheetComfort)
        bottomSheetViewPremium = binding!!.root.findViewById(R.id.layoutBottomSheetPremium)

        binding?.bottomSheet?.setOnClickListener{onButtonClickListener()}
        binding?.rollUp?.setOnClickListener{onButtonUpClickListener()}

        binding?.bottomSheetComfort?.setOnClickListener{onButtonComfortClickListener()}
        binding?.rollUp2?.setOnClickListener{onButtonUp2ClickListener()}

        binding?.bottomSheetPremium?.setOnClickListener{onButtonPremiumClickListener()}
        binding?.rollUp3?.setOnClickListener{onButtonUp3ClickListener()}

        binding?.checkBox?.setOnClickListener{onButtonStartClickListener()}
        binding?.checkBox2?.setOnClickListener {onComfortClickListener()}
        binding?.checkBox3?.setOnClickListener {onPremiumClickListener()}

        binding?.buttonChooseATariff?.setOnClickListener{}//переход на другой экран - выбор агрегатора
    }

    fun onButtonClickListener() {
        bottomSheetViewStart.visibility = View.VISIBLE
        binding?.bottomSheet?.visibility = View.GONE
        binding?.rollUp?.visibility = View.VISIBLE
    }

    fun onButtonUpClickListener() {
        bottomSheetViewStart.visibility = View.GONE
        binding?.bottomSheet?.visibility = View.VISIBLE
        binding?.rollUp?.visibility = View.GONE
    }

    fun onButtonComfortClickListener() {
        bottomSheetViewComfort.visibility = View.VISIBLE
        binding?.bottomSheetComfort?.visibility = View.GONE
        binding?.rollUp2?.visibility = View.VISIBLE
    }

    fun onButtonUp2ClickListener() {
        bottomSheetViewComfort.visibility = View.GONE
        binding?.bottomSheetComfort?.visibility = View.VISIBLE
        binding?.rollUp2?.visibility = View.GONE
    }

    fun onButtonPremiumClickListener() {
        bottomSheetViewPremium.visibility = View.VISIBLE
        binding?.bottomSheetPremium?.visibility = View.GONE
        binding?.rollUp3?.visibility = View.VISIBLE
    }

    fun onButtonUp3ClickListener() {
        bottomSheetViewPremium.visibility = View.GONE
        binding?.bottomSheetPremium?.visibility = View.VISIBLE
        binding?.rollUp3?.visibility = View.GONE
    }

    fun onButtonStartClickListener() {
        var start = "Выбрать тариф Старт"
        binding?.buttonChooseATariff?.text = start
        binding?.checkBox2?.isChecked = false
        binding?.checkBox3?.isChecked = false
    }

    fun onComfortClickListener(){
        var comfort = "Выбрать тариф Комфорт"
        binding?.buttonChooseATariff?.text = comfort
        binding?.checkBox?.isChecked = false
        binding?.checkBox3?.isChecked = false
    }

    fun onPremiumClickListener(){
        var premium = "Выбрать тариф Премиум"
        binding?.buttonChooseATariff?.text = premium
        binding?.checkBox?.isChecked = false
        binding?.checkBox2?.isChecked = false
    }
}