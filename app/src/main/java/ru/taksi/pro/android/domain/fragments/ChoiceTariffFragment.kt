package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.ChoiceTariffFragmentBinding
import ru.taksi.pro.android.domain.adapters.RVAdapterChoiceTariff
import ru.taksi.pro.android.mvvm.model.entity.tariffs.Tariff
import ru.taksi.pro.android.mvvm.vm.ChoiceTariffViewModel
import javax.inject.Inject

class ChoiceTariffFragment : Fragment() {
    private lateinit var binding: ChoiceTariffFragmentBinding
    private var adapter: RVAdapterChoiceTariff? = null

    @Inject
    lateinit var viewModel: ChoiceTariffViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        TaxiProApplication.component.inject(this)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.choice_tariff_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        initViewModule()
        initComponent()
    }

    private fun initComponent() {
        binding.buttonChooseATariff.setOnClickListener {
            adapter?.checkedName?.let{
                if (it != "") {
                    viewModel.setTariff(it)
                    viewModel.onCleared()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ChoiceAggregatorFragment()).commit()
                }
            }
        }
    }

    private fun initViewModule() {
        viewModel.getAnswerLiveData().observe(requireActivity(), { value ->
            adapter?.setData(value as MutableList<Tariff>)
            viewModel.getTariff()?.let {
                adapter?.setTariff(it)
            }
        })
        viewModel.downloadTariffs()
    }

    private fun initRV() {
        binding.rvTariffs.layoutManager =
            LinearLayoutManager(requireContext())
        adapter = RVAdapterChoiceTariff(requireContext())
        binding.rvTariffs.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        adapter?.checkedName?.let {
            if (it != "") {
                viewModel.setTariff(it)
            }
        }
    }
}