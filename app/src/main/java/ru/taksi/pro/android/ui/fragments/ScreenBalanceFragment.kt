package ru.taksi.pro.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ScreenBalanceFragmentBinding

class ScreenBalanceFragment : Fragment() {
    private lateinit var binding: ScreenBalanceFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.screen_balance_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            myBalanceAggregatorYndexTaxi.visibility =
                View.GONE // не показывать агрегатор яндекс такси
            myBalanceAggregatorUber.visibility = View.GONE // не показывать агрегатор Uber
            buttonWithdrawMoney.setOnClickListener { withdrawFunds() }
        }
    }

    fun withdrawFunds() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenWithdrawFunds())
            .commit()
    }
}