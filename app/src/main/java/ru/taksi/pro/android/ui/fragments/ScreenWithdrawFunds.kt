package ru.taksi.pro.android.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ScreenWithdrawFundsFragmentBinding

class ScreenWithdrawFunds : Fragment() {
    private lateinit var binding: ScreenWithdrawFundsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.screen_withdraw_funds_fragment,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            myAggregatorYndexTaxi.visibility = View.GONE // не показывать агрегатор яндекс такси
            myAggregatorUber.visibility = View.GONE // не показывать агрегатор Uber
            textTotalAmount.setOnClickListener {
                totalAmount()
            }
            buttonArrowRight.setOnClickListener { myBalance() } // назад в баланс
            bankCardDell1.setOnClickListener { dellBankCard1() } // удалить банк. карту 1
            bankCardDell2.setOnClickListener { dellBankCard2() } // удалить банк. карту 2
        }
    }

    fun totalAmount() {
        var a = 0
        var b = 0
        var c = 0
        var d = 0
        val gett = binding.inputGett
        val citimobile = binding.inputCitimobile
        val yndexTaxi = binding.inputYndexTaxi
        val uber = binding.inputUber
        if(!TextUtils.isEmpty(gett.getText().toString())) {
            a = Integer.parseInt(gett.getText().toString())
        } else {
            a = 0
        }
        if(!TextUtils.isEmpty(citimobile.getText().toString())) {
            b = Integer.parseInt(citimobile.getText().toString())
        } else {
            b = 0
        }
        if(!TextUtils.isEmpty(yndexTaxi.getText().toString())) {
            c = Integer.parseInt(yndexTaxi.getText().toString())
        } else {
            c = 0
        }
        if(!TextUtils.isEmpty(uber.getText().toString())) {
            d = Integer.parseInt(uber.getText().toString())
        } else {
            d = 0
        }
        val sum = a + b + c + d
        binding.totalAmount.text = sum.toString()
    }

    fun myBalance() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenBalanceFragment()).commit()
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
}