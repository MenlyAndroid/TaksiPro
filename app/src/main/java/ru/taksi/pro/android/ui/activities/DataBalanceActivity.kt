package ru.taksi.pro.android.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.DataBalanceActivityBinding
import ru.taksi.pro.android.mvvm.data.EventArgs
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.vm.DataBalanceViewModel
import ru.taksi.pro.android.ui.dialogs.ExitDialogFragment
import ru.taksi.pro.android.ui.fragments.ScreenBalanceFragment
import ru.taksi.pro.android.ui.fragments.ScreenMyDetailsFragment
import ru.taksi.pro.android.ui.fragments.ScreenWithdrawFunds
import ru.taksi.pro.android.ui.view.IExitView
import javax.inject.Inject


class DataBalanceActivity : IExitView, AppCompatActivity() {
    private lateinit var binding: DataBalanceActivityBinding

    @Inject
    lateinit var viewModel: DataBalanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.data_balance_activity)
        TaxiProApplication.component.inject(this)
        initComponent()
        Log.d("!!!", "onCreate")
        initViewModel()
    }

    private fun initComponent() {
        binding.run {
            bottomMyData.setOnClickListener { screenData() } // мои данные
            bottomBalance.setOnClickListener { screenBalance() } // мой баланс
            bottomWithdrawFunds.setOnClickListener { screenWithdrawFunds() } // вывод средств
        }
    }

    private fun initViewModel() {
        Log.d("!!!", "initViewModel $viewModel")
        viewModel.getAnswerLiveData().observe(this, { value ->
            when (value.event) {
                EventArgs.ERROR -> {
                    val toast = Toast.makeText(this, value.text, Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                }
                EventArgs.PROCESS -> {
                    binding.animationView.visibility = View.VISIBLE
                    supportFragmentManager.findFragmentById(R.id.container)?.let {
                        supportFragmentManager.beginTransaction()
                            .hide(it)
                            .commit()
                    }
                }
                EventArgs.DONE -> {
                    binding.animationView.visibility = View.GONE
                    screenData()
                }
            }
        })
        Log.d("!!!", "initViewModel2 $viewModel")
        viewModel.getUserProfile()
    }

    // мои данные
    fun screenData() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenMyDetailsFragment())
            .commit()
    }

    // мой баланс
    fun screenBalance() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenBalanceFragment())
            .commit()

    }

    // вывод средств
    fun screenWithdrawFunds() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenWithdrawFunds())
            .commit()
    }

    override fun onBackPressed() {
        ExitDialogFragment(this).show(supportFragmentManager, "dialog")
    }

    override fun onExit() {
        UserProperties.instance.toDestroy()
        super.onBackPressed()
    }
}
