package ru.taksi.pro.android.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.RegistrFragmentBinding
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.vm.RegistrationPhoneViewModel
import javax.inject.Inject


class RegistrationFragment : Fragment() {
    private lateinit var binding: RegistrFragmentBinding
    private var phone: String = ""

    @Inject
    lateinit var viewModel: RegistrationPhoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        TaxiProApplication.component.inject(this)
        if(viewModel.getToken() != null) requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, RegistrationFragmentChoiceTariff()).commit()
        binding =
            DataBindingUtil.inflate(inflater, R.layout.registr_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onContentChanged()
        binding.buttonCod.setOnClickListener { onButtonClickListener() }
        binding.editTextPhone.let {
            it.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (it.rawText?.length == 10) {
                        phone = "7${it.rawText}"
                        UserProperties.instance.phone = phone
                        binding.buttonCod.isEnabled = true
                    } else {
                        phone = ""
                        binding.buttonCod.isEnabled = false
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
        viewModel.getAnswerLiveData().observe(requireActivity(), { value ->
            if (value == "true") {
                Log.d("!!!", "true")
                viewModel.onCleared()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegistrationFragmentEnterCode.newInstance(phone)).commit()
            } else {
                val toast = Toast.makeText(requireActivity(), value, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        })
    }

    private fun onButtonClickListener() {
        binding.editTextPhone.let {
            viewModel.sendPhone(phone)
        }
        Log.d("!!!", "click: ${binding.editTextPhone.rawText} ${viewModel}")
    }
}