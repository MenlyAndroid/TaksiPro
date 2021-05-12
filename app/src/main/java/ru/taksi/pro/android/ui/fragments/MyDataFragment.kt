package ru.taksi.pro.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.MyDataFragmentBinding

class MyDataFragment : Fragment() {
    private lateinit var binding: MyDataFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.my_data_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            bottomMyData.setOnClickListener { myData() } // мои данные
            bottomBalance.setOnClickListener { myBalance() } // мой баланс
            bottomWithdrawFunds.setOnClickListener { withdrawFunds() } // вывод средств
            bottomMyTariff.setOnClickListener { onButtonMyTariff() } // мой тариф
            myTariffRollUp.setOnClickListener { onButtonMyTariffUp() } // свернуть мой тариф
            bottomMyAuto.setOnClickListener { onButtonMyAuto() } // мой автомобиль
            myAutoRollUp.setOnClickListener { onButtonMyAutoUp() } // свернуть мой автомобиль
            bottomAggregatorsAll.setOnClickListener { myAggregators() } // мои агрегаторы
            myAggregatorsRollUp.setOnClickListener { onAggregatorsUp() } // свернуть мои агрегаторы
            bottomChooseTariffNextMonth.setOnClickListener { onChooseTariffNextMonth() } // изменить тариф на следующий месяц
            buttonEnterNewTariff.setOnClickListener { onSaveNewTariffNextMonth() } // сохранить новый тариф
            checkBoxStart.setOnClickListener { onButtonStartClickListener() } // выбрать тариф Старт
            checkBoxComfort.setOnClickListener { onComfortClickListener() } // выбрать тариф Комфорт
            checkBoxPremium.setOnClickListener { onPremiumClickListener() } // выбрать тариф Премиум
            buttonWithdrawMoney.setOnClickListener { withdrawFunds() } // кнопка "вывести деньги" окно "Баланс"
            buttonWithdrawTotalAmount.setOnClickListener { onWithdrawFunds() }  // кнопка "вывести деньги" окно "Вывод средств"
            buttonArrowRight.setOnClickListener { myBalance() } // назад в баланс
            bankCardDell1.setOnClickListener { dellBankCard1() } // удалить банк. карту 1
            bankCardDell2.setOnClickListener { dellBankCard2() } // удалить банк. карту 2
            animationView.visibility = View.VISIBLE  // песочные часы
        }
    }

    // мои данные
    fun myData() {
        binding.run {
            bottomMyDataVisible.visibility = View.VISIBLE
            bottomMyBalanceVisible.visibility = View.GONE
            bottomOnWithdrawFunds.visibility = View.GONE
            animationView.visibility = View.GONE
            bottomMyData.setBackgroundResource(R.drawable.button_main)
            bottomBalance.setBackgroundResource(R.drawable.button_main_shape_pressed)
            bottomWithdrawFunds.setBackgroundResource(R.drawable.button_main_shape_pressed)
        }
    }

    // мой баланс
    fun myBalance() {
        binding.run {
            bottomMyBalanceVisible.visibility = View.VISIBLE
            bottomMyDataVisible.visibility = View.GONE
            bottomOnWithdrawFunds.visibility = View.GONE
            animationView.visibility = View.GONE
            myBalanceAggregatorYndexTaxi.visibility =
                View.GONE // не показывать агрегатор яндекс “акси
            myBalanceAggregatorUber.visibility = View.GONE // не показывать агрегатор Uber
            bottomBalance.setBackgroundResource(R.drawable.button_main)
            bottomMyData.setBackgroundResource(R.drawable.button_main_shape_pressed)
            bottomWithdrawFunds.setBackgroundResource(R.drawable.button_main_shape_pressed)
        }
    }

    // вывод средств
    fun withdrawFunds() {
        binding.run {
            bottomOnWithdrawFunds.visibility = View.VISIBLE
            bottomMyDataVisible.visibility = View.GONE
            bottomMyBalanceVisible.visibility = View.GONE
            animationView.visibility = View.GONE
            myAggregatorYndexTaxi.visibility = View.GONE // не показывать агрегатор яндекс такси
            myAggregatorUber.visibility = View.GONE // не показывать агрегатор Uber
            bottomWithdrawFunds.setBackgroundResource(R.drawable.button_main)
            bottomMyData.setBackgroundResource(R.drawable.button_main_shape_pressed)
            bottomBalance.setBackgroundResource(R.drawable.button_main_shape_pressed)
        }
    }

    // мои агрегаторы
    fun myAggregators() {
        binding.run {
            bottomAggregators.visibility = View.VISIBLE
            myAggregatorsRollUp.visibility = View.VISIBLE
            myAggregatorsRollUn.visibility = View.GONE
        }
    }

    // свернуть мои агрегаторы
    fun onAggregatorsUp() {
        binding.run {
            bottomAggregators.visibility = View.GONE
            myAggregatorsRollUp.visibility = View.GONE
            myAggregatorsRollUn.visibility = View.VISIBLE
        }
    }

    // мой тариф
    fun onButtonMyTariff() {
        var tariff = 3
        binding.run {
            bottomMyTariffs.visibility = View.VISIBLE
            myTariffRollUp.visibility = View.VISIBLE
            myTariffRollUn.visibility = View.GONE
            if (tariff == 1) {
                buttonStart.visibility = View.VISIBLE
                bottomMyTariffStart.visibility = View.GONE
                bottomMyTariffComfort.visibility = View.VISIBLE
                bottomMyTariffPremium.visibility = View.VISIBLE
            }
            if (tariff == 2) {
                buttonComfort.visibility = View.VISIBLE
                bottomMyTariffComfort.visibility = View.GONE
            }
            if (tariff == 3) {
                buttonPremium.visibility = View.VISIBLE
                bottomMyTariffPremium.visibility = View.GONE
                bottomMyTariffStart.visibility = View.VISIBLE
                bottomMyTariffComfort.visibility = View.VISIBLE
            }

            bottomChooseTariffNextMonth.visibility = View.VISIBLE
        }
    }

    // свернуть мой тариф
    fun onButtonMyTariffUp() {
        binding.run {
            bottomMyTariffs.visibility = View.GONE
            myTariffRollUp.visibility = View.GONE
            myTariffRollUn.visibility = View.VISIBLE
            bottomChooseTariffNextMonthAll.visibility = View.GONE
        }
    }

    // изменить тариф на следующий мес¤ц
    fun onChooseTariffNextMonth() {
        binding.run {
            bottomChooseTariffNextMonth.visibility = View.GONE
            bottomChooseTariffNextMonthAll.visibility = View.VISIBLE
        }
    }

    // сохранить новый тариф
    fun onSaveNewTariffNextMonth() {
        binding.run {
            bottomChooseTariffNextMonthAll.visibility = View.GONE
            bottomChooseTariffNextMonth.visibility = View.VISIBLE
        }
    }

    // мой автомобиль
    fun onButtonMyAuto() {
        binding.run {
            bottomMyAutoData.visibility = View.VISIBLE
            myAutoRollUp.visibility = View.VISIBLE
            myAutoRollUn.visibility = View.GONE
        }
    }

    // свернуть мой автомобиль
    fun onButtonMyAutoUp() {
        binding.run {
            bottomMyAutoData.visibility = View.GONE
            myAutoRollUp.visibility = View.GONE
            myAutoRollUn.visibility = View.VISIBLE
        }
    }

    fun onButtonStartClickListener() {
        binding.run {
            checkBoxComfort.isChecked = false
            checkBoxPremium.isChecked = false
        }
    }

    fun onComfortClickListener() {
        binding.run {
            checkBoxStart.isChecked = false
            checkBoxPremium.isChecked = false
        }
    }

    fun onPremiumClickListener() {
        binding.run {
            checkBoxStart.isChecked = false
            checkBoxComfort.isChecked = false
        }
    }

    fun dellBankCard1() {
        binding.run {
            radioButton1.visibility = View.GONE
            bankCardDell1.visibility = View.GONE
        }
    }

    fun dellBankCard2() {
        binding.run {
            radioButton1.text = "Сбер зеленая 3339030"
            radioButton2.visibility = View.GONE
            bankCardDell2.visibility = View.GONE
        }
    }

    fun onWithdrawFunds() {

    }
}
