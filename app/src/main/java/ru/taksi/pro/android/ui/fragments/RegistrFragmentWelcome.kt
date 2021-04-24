package ru.taksi.pro.android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.taksi.pro.android.databinding.RegistrFragmentWelcomeBinding

private const val HISTORY_ACTIVITY_PATH = "ru.taksi.pro.android.ui.activities.ChoiceTariffActivity"
class RegistrFragmentWelcome: Fragment() {
    private var binding: RegistrFragmentWelcomeBinding? = null
    lateinit var context: AppCompatActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = RegistrFragmentWelcomeBinding.inflate(inflater).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.buttonEnterFill?.setOnClickListener{onButtonClickListener()}
    }

    fun onButtonClickListener() {
    }

}