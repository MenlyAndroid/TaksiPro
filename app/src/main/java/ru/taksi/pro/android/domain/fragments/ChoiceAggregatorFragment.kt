package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.databinding.ChoiceAggregatorFragmentBinding


class ChoiceAggregatorFragment: Fragment() {
private var binding: ChoiceAggregatorFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ChoiceAggregatorFragmentBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.checkboxGett?.setOnClickListener{onCheckboxGett()}
        binding?.checkboxUber?.setOnClickListener {onCheckboxUber()}
        binding?.checkboxYandexTaxi?.setOnClickListener {onCheckboxYandexTaxi()}
        binding?.checkboxCityMobile?.setOnClickListener {onCheckboxCityMobile()}

        binding?.buttonChoose?.setOnClickListener{}//переход на другой экран - заполнить паспортные данные
    }

    fun onCheckboxGett() {
    }

    fun onCheckboxUber(){
    }

    fun onCheckboxCityMobile(){
    }

    fun onCheckboxYandexTaxi(){
    }
}