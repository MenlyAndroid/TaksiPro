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
import ru.ildus.taksipro.databinding.RegistrFragmentWelcomeBinding

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
  //      button.setOnClickListener{onButtonClickListener()}
    }

    fun onButtonClickListener() {

    }

}