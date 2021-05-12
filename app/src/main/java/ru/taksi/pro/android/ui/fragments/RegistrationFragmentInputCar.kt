package ru.taksi.pro.android.ui.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.databinding.InputCarFragmentBinding
import ru.taksi.pro.android.mvvm.data.EventArgs
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.vm.InputCarViewModel
import javax.inject.Inject

class RegistrationFragmentInputCar : Fragment() {
    private lateinit var binding: InputCarFragmentBinding
    private var isRegistration = true

    @Inject
    lateinit var viewModel: InputCarViewModel

    companion object {
        private const val IS_REGISTRATION = "is registration"
        fun newInstance(isRegistration: Boolean) = RegistrationFragmentInputCar().apply {
            arguments = Bundle().apply {
                putBoolean(IS_REGISTRATION, isRegistration)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            isRegistration = it.getBoolean(IS_REGISTRATION, true)
        }
        TaxiProApplication.component.inject(this)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.input_car_fragment,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onContentChanged()
        setComponentsValue()
        initComponents()
        initViewModule()
    }

    private fun initViewModule() {
        requireActivity().let {
            viewModel.getAnswerLiveData().observe(it, { value ->
                when (value.event) {
                    EventArgs.DONE -> {
                        viewModel.onCleared()
                        it.supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ScreenMyDetailsFragment()).commit()
                    }
                    EventArgs.ERROR -> {
                        val toast =
                            Toast.makeText(requireActivity(), value.text, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    }
                }
            })
        }
    }

    private fun setComponentsValue() {
        UserProperties.instance.carBrand?.let {
            binding.carBrand.setText(it)
        }
        UserProperties.instance.carModel?.let {
            binding.carModel.setText(it)
        }
        UserProperties.instance.carYear?.let {
            binding.carYear.setText(it)
        }
        UserProperties.instance.carColor?.let {
            binding.carColor.setText(it)
        }
        UserProperties.instance.carNumber?.let {
            binding.carNumber.setText(it)
        }
        UserProperties.instance.carWIN?.let {
            binding.carWin.setText(it)
        }
        UserProperties.instance.carCertificate?.let {
            binding.carCertificate.setText(it)
        }
        UserProperties.instance.licenseNumber?.let {
            binding.licenseNumber.setText(it)
        }
    }

    private fun initComponents() {
        binding.carBrand.addTextChangedListener {
            UserProperties.instance.carBrand = it.toString()
        }
        binding.carModel.addTextChangedListener {
            UserProperties.instance.carModel = it.toString()
        }
        binding.carYear.addTextChangedListener {
            UserProperties.instance.carYear = it.toString()
        }
        binding.carColor.addTextChangedListener {
            UserProperties.instance.carColor = it.toString()
        }
        binding.carNumber.addTextChangedListener {
            UserProperties.instance.carNumber = it.toString()
        }
        binding.carWin.addTextChangedListener {
            UserProperties.instance.carWIN = it.toString()
        }

        binding.carCertificate.addTextChangedListener {
            UserProperties.instance.carCertificate = it.toString()
        }
        binding.licenseNumber.addTextChangedListener {
            UserProperties.instance.licenseNumber = it.toString()
        }
        binding.btnNext.setOnClickListener {
            if (!isRegistration) {
                viewModel.sendRegistrationData()
            } else {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegistrationFragmentCheckInputData()).commit()
            }
        }
    }
}