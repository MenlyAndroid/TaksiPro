package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.mvvm.vm.RegistrationAddPhotoViewModel
import javax.inject.Inject

class RegistrationFragmentAddPhoto: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onButtonClickListener(){
        Log.d("!!!", "onButtonClickListener: ")
    }
}
