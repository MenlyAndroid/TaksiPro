package ru.ildus.taksipro.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import ru.ildus.taksipro.databinding.RegistrFragmentEnterCodeBinding

class RegistrFragmentEnterCode: Fragment() {
    private var binding: RegistrFragmentEnterCodeBinding? = null
    private lateinit var editText: EditText
    private lateinit var button: Button
    companion object {
        fun newInstance() = RegistrFragmentEnterCode()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = RegistrFragmentEnterCodeBinding.inflate(inflater).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText = binding!!.editTextPhone
        button = binding!!.buttonEnterCod
        button.setOnClickListener{onButtonClickListener()}
    }

    fun onButtonClickListener() {

    }
}