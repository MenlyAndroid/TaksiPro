package ru.ildus.taksipro.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ildus.taksipro.databinding.RegistrFragmentBinding


class RegistrFragment : Fragment() {
    private var binding: RegistrFragmentBinding? = null
    private lateinit var editText: EditText
    private lateinit var button: Button
    lateinit var context: AppCompatActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = RegistrFragmentBinding.inflate(inflater).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText = binding!!.editTextPhone
        button = binding!!.buttonCod
        button.setOnClickListener{onButtonClickListener()}
    }

    fun onButtonClickListener() {

    }

}