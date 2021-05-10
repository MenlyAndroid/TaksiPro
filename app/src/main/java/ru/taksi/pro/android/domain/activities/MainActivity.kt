package ru.taksi.pro.android.domain.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.ActivityMainBinding
import ru.taksi.pro.android.domain.adapters.RVAdapterMainTariff
import ru.taksi.pro.android.mvvm.vm.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var tariffAdapter: RVAdapterMainTariff? = null

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TaxiProApplication.component.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRV()
        initViewModule()
        initComponents()
    }

    private fun initViewModule() {
        viewModel.getTariffLiveData().observe(this, { value ->
            tariffAdapter?.setData(value)
        })
        viewModel.downloadTariffs()
    }

    private fun initComponents() {
        binding.button3.setOnClickListener { onButtonsClickListener() }
        binding.button4.setOnClickListener { onButtonsClickListener() }
        binding.button5.setOnClickListener { onButtonsClickListener() }
        binding.buttonOpen.setOnClickListener { onButtonsClickListener() }
    }

    private fun onButtonsClickListener() {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }

    private fun initRV() {
        binding.rvTariffs.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tariffAdapter = RVAdapterMainTariff(this)
        binding.rvTariffs.adapter = tariffAdapter
    }
}
