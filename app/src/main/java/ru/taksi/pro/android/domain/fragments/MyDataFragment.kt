package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.MyDataFragmentBinding


class MyDataFragment : Fragment() {
    private var binding: MyDataFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.my_data_fragment, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.bottomMyData?.setOnClickListener { myData() } // мои данные
        binding?.bottomBalance?.setOnClickListener { myBalance() } // мой баланс
        binding?.bottomWithdrawFunds?.setOnClickListener { withdrawFunds() } // вывод средств
        binding?.bottomMyTariff?.setOnClickListener { onButtonMyTariff() } // мой тариф
        binding?.myTariffRollUp?.setOnClickListener { onButtonMyTariffUp() } // свернуть мой тариф
        binding?.bottomMyAuto?.setOnClickListener { onButtonMyAuto() } // мой автомобиль
        binding?.myAutoRollUp?.setOnClickListener { onButtonMyAutoUp() } // свернуть мой автомобиль
        binding?.bottomAggregatorsAll?.setOnClickListener { myAggregators() } // мои агрегаторы
        binding?.myAggregatorsRollUp?.setOnClickListener { onAggregatorsUp() } // свернуть мои агрегаторы
        binding?.bottomChooseTariffNextMonth?.setOnClickListener { onChooseTariffNextMonth() } // изменить тариф на следующий месяц
        binding?.buttonEnterNewTariff?.setOnClickListener { onSaveNewTariffNextMonth() } // сохранить новый тариф
        binding?.checkBoxStart?.setOnClickListener { onButtonStartClickListener() } // выбрать тариф Старт
        binding?.checkBoxComfort?.setOnClickListener { onComfortClickListener() } // выбрать тариф Комфорт
        binding?.checkBoxPremium?.setOnClickListener { onPremiumClickListener() } // выбрать тариф Премиум
        binding?.animationView?.visibility = View.VISIBLE  // песочные часы

    }

    // мои данные
    fun myData() {
        binding?.bottomMyDataVisible?.visibility = View.VISIBLE
        binding?.bottomMyBalanseVisible?.visibility = View.GONE
        binding?.animationView?.visibility = View.GONE
        binding?.bottomMyData?.setBackgroundResource(R.drawable.button_main)
        binding?.bottomBalance?.setBackgroundResource(R.drawable.button_main_shape_pressed)
        binding?.bottomWithdrawFunds?.setBackgroundResource(R.drawable.button_main_shape_pressed)
    }

    // мой баланс
    fun myBalance() {
        binding?.bottomMyBalanseVisible?.visibility = View.VISIBLE
        binding?.bottomMyDataVisible?.visibility = View.GONE
        binding?.animationView?.visibility = View.GONE
        binding?.bottomBalance?.setBackgroundResource(R.drawable.button_main)
        binding?.bottomMyData?.setBackgroundResource(R.drawable.button_main_shape_pressed)
        binding?.bottomWithdrawFunds?.setBackgroundResource(R.drawable.button_main_shape_pressed)
    }

    // вывод средств
    fun withdrawFunds() {
        binding?.bottomWithdrawFunds?.setBackgroundResource(R.drawable.button_main)
        binding?.bottomMyData?.setBackgroundResource(R.drawable.button_main_shape_pressed)
        binding?.bottomBalance?.setBackgroundResource(R.drawable.button_main_shape_pressed)
    }

    // мои агрегаторы
    fun myAggregators() {
        binding?.bottomAggregators?.visibility = View.VISIBLE
        binding?.myAggregatorsRollUp?.visibility = View.VISIBLE
        binding?.myAggregatorsRollUn?.visibility = View.GONE
    }

    // свернуть мои агрегаторы
    fun onAggregatorsUp() {
        binding?.bottomAggregators?.visibility = View.GONE
        binding?.myAggregatorsRollUp?.visibility = View.GONE
        binding?.myAggregatorsRollUn?.visibility = View.VISIBLE
    }

    // мой тариф
    fun onButtonMyTariff() {
        var tariff = 3
        binding?.bottomMyTariffs?.visibility = View.VISIBLE
        binding?.myTariffRollUp?.visibility = View.VISIBLE
        binding?.myTariffRollUn?.visibility = View.GONE
        if (tariff == 1) {
            binding?.buttonStart?.visibility = View.VISIBLE
            binding?.bottomMyTariffStart?.visibility = View.GONE
            binding?.bottomMyTariffComfort?.visibility = View.VISIBLE
            binding?.bottomMyTariffPremium?.visibility = View.VISIBLE
        }
        if (tariff == 2) {
            binding?.buttonComfort?.visibility = View.VISIBLE
            binding?.bottomMyTariffComfort?.visibility = View.GONE
        }
        if (tariff == 3) {
            binding?.buttonPremium?.visibility = View.VISIBLE
            binding?.bottomMyTariffPremium?.visibility = View.GONE
            binding?.bottomMyTariffStart?.visibility = View.VISIBLE
            binding?.bottomMyTariffComfort?.visibility = View.VISIBLE
        }

        binding?.bottomChooseTariffNextMonth?.visibility = View.VISIBLE
    }

    // свернуть мой тариф
    fun onButtonMyTariffUp() {
        binding?.bottomMyTariffs?.visibility = View.GONE
        binding?.myTariffRollUp?.visibility = View.GONE
        binding?.myTariffRollUn?.visibility = View.VISIBLE
        binding?.bottomChooseTariffNextMonthAll?.visibility = View.GONE
    }

    // изменить тариф на следующий месяц
    fun onChooseTariffNextMonth() {
        binding?.bottomChooseTariffNextMonth?.visibility = View.GONE
        binding?.bottomChooseTariffNextMonthAll?.visibility = View.VISIBLE
    }

    // сохранить новый тариф
    fun onSaveNewTariffNextMonth() {
        binding?.bottomChooseTariffNextMonthAll?.visibility = View.GONE
        binding?.bottomChooseTariffNextMonth?.visibility = View.VISIBLE
    }

    // мой автомобиль
    fun onButtonMyAuto() {
        binding?.bottomMyAutoData?.visibility = View.VISIBLE
        binding?.myAutoRollUp?.visibility = View.VISIBLE
        binding?.myAutoRollUn?.visibility = View.GONE
    }

    // свернуть мой автомобиль
    fun onButtonMyAutoUp() {
        binding?.bottomMyAutoData?.visibility = View.GONE
        binding?.myAutoRollUp?.visibility = View.GONE
        binding?.myAutoRollUn?.visibility = View.VISIBLE
    }

    fun onButtonStartClickListener() {
        binding?.checkBoxComfort?.isChecked = false
        binding?.checkBoxPremium?.isChecked = false
    }

    fun onComfortClickListener() {
        binding?.checkBoxStart?.isChecked = false
        binding?.checkBoxPremium?.isChecked = false
    }

    fun onPremiumClickListener() {
        binding?.checkBoxStart?.isChecked = false
        binding?.checkBoxComfort?.isChecked = false
    }
}



