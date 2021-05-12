package ru.taksi.pro.android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.RegistrFragmentEnterCodeBinding
import ru.taksi.pro.android.ui.activities.DataBalanceActivity
import ru.taksi.pro.android.mvvm.vm.RegistrationCodeViewModel
import javax.inject.Inject

class RegistrationFragmentEnterCode : Fragment() {
    companion object {
        private const val PHONE = "phone"
        fun newInstance(phone: String) = RegistrationFragmentEnterCode().apply {
            arguments = Bundle().apply {
                putString(PHONE, phone)
            }
        }
    }

    private lateinit var binding: RegistrFragmentEnterCodeBinding
    private var phone: String = ""
    private var code: String = ""

    @Inject
    lateinit var viewModel: RegistrationCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        TaxiProApplication.component.inject(this)
        arguments?.let {
            phone = it.getString(PHONE).toString()
        }
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.registr_fragment_enter_code,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onContentChanged()
        initComponent()
        initViewModel()
    }

    private fun initViewModel() {
        requireActivity().let{
            viewModel.getAnswerLiveData().observe(it, { value ->
                when (value) {
                    RegistrationCodeViewModel.CODE_NEW_USER -> {
                        viewModel.onCleared()
                        it.supportFragmentManager.beginTransaction()
                            .replace(R.id.container, RegistrationFragmentWelcome()).commit()
                    }
                    RegistrationCodeViewModel.CODE_AUTHORISED_USER -> {
                            it.startActivity(Intent(it, DataBalanceActivity::class.java))
                            it.finish()
                    }
                    else -> {
                        val toast = Toast.makeText(requireActivity(), value, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    }
                }
            })
        }
    }

    private fun initComponent() {
        binding.buttonEnterCod.setOnClickListener { viewModel.sendCode(phone, code) }
        binding.editTextCode.let {
            it.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (it.text?.length == 4) {
                        code = it.text.toString()
                        binding.buttonEnterCod.isEnabled = true
                    } else {
                        code = ""
                        binding.buttonEnterCod.isEnabled = false
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })
        }
    }
}
