package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.databinding.RegistrFragmentAddPhotoBinding


class RegistrationFragmentAddPhoto : Fragment() {
    private lateinit var binding: RegistrFragmentAddPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = RegistrFragmentAddPhotoBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAddPhoto.setOnClickListener { onButtonClickListener() }
        Log.d("!!!", "onViewCreated: ")
    }

    fun onButtonClickListener() {
        Log.d("!!!", "onButtonClickListener: ")
    }
}
