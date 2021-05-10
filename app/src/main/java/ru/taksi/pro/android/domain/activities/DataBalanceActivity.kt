package ru.taksi.pro.android.domain.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.DataBalanceActivityBinding
import ru.taksi.pro.android.domain.fragments.ScreenBalanceFragment
import ru.taksi.pro.android.domain.fragments.ScreenMyDetailsFragment
import ru.taksi.pro.android.domain.fragments.ScreenWithdrawFunds
import ru.taksi.pro.android.mvvm.data.UserProperties


class DataBalanceActivity : AppCompatActivity() {
    lateinit var binding: DataBalanceActivityBinding
    private val FIRST_FRAG_TAG = "f1"
    private val TWO_FRAG_TAG = "f2"
    private val THREE_FRAG_TAG = "f3"
    val screenMyDetailsFragment =
        supportFragmentManager.findFragmentByTag(FIRST_FRAG_TAG)
    val screenBalanceFragment =
        supportFragmentManager.findFragmentByTag(TWO_FRAG_TAG)
    val screenWithdrawFunds =
        supportFragmentManager.findFragmentByTag(THREE_FRAG_TAG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.data_balance_activity)
        if (UserProperties.instance.token == null) {
            binding.animationView.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ScreenMyDetailsFragment())
                .commit()
        }

        binding.run {
            bottomMyData.setOnClickListener { screenData() } // мои данные
            bottomBalance.setOnClickListener { screenBalance() } // мой баланс
            bottomWithdrawFunds.setOnClickListener { screenWithdrawFunds() } // вывод средств
        }
    }

    // мои данные
    fun screenData() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenMyDetailsFragment(), FIRST_FRAG_TAG)
            .commit()
        binding.run {
            animationView.visibility = View.GONE
            bottomMyData.setBackgroundResource(R.drawable.button_main)
            bottomBalance.setBackgroundResource(R.drawable.button_main_shape_pressed)
            bottomWithdrawFunds.setBackgroundResource(R.drawable.button_main_shape_pressed)

        }
    }

    // мой баланс
    fun screenBalance() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenBalanceFragment())
            .commit()

        if (screenMyDetailsFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ScreenBalanceFragment(), TWO_FRAG_TAG)
                .hide(screenMyDetailsFragment)
                .commit()
        }
        binding.run {
            animationView.visibility = View.GONE
            bottomBalance.setBackgroundResource(R.drawable.button_main)
            bottomMyData.setBackgroundResource(R.drawable.button_main_shape_pressed)
            bottomWithdrawFunds.setBackgroundResource(R.drawable.button_main_shape_pressed)
        }

    }

    // вывод средств
    fun screenWithdrawFunds() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScreenWithdrawFunds())
            .commit()

        binding.run {
            animationView.visibility = View.GONE
            bottomWithdrawFunds.setBackgroundResource(R.drawable.button_main)
            bottomMyData.setBackgroundResource(R.drawable.button_main_shape_pressed)
            bottomBalance.setBackgroundResource(R.drawable.button_main_shape_pressed)
        }
    }
}
