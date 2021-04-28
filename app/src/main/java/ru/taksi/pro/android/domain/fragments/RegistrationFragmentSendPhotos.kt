package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrFragmentSendPhotoBinding

class RegistrationFragmentSendPhotos: Fragment() {
    private var binding: RegistrFragmentSendPhotoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = RegistrFragmentSendPhotoBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }
}
