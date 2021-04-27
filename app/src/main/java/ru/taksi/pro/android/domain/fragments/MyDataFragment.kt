package ru.taksi.pro.android.domain.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.MyDataFragmentBinding

class MyDataFragment : Fragment() {
    private var binding: MyDataFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.my_data_fragment, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.bottomMyDataVisible?.visibility = View.VISIBLE
        binding?.bottomMyTariff?.setOnClickListener {onButtonMyTariff() }
        binding?.rollUp?.setOnClickListener {onButtonMyTariffUp() }
        binding?.bottomMyAuto?.setOnClickListener {onButtonMyAutoUp()}
        binding?.animationView?.visibility = view.visibility  //песочные часы

    }

    fun onButtonMyTariff() {
        var tariff = 1
     binding?.bottomMyTariffs?.visibility = View.VISIBLE
        binding?.rollUp?.visibility = View.VISIBLE
        if(tariff ==1){
            binding?.buttonStart?.visibility = View.VISIBLE
        }
    }

    fun onButtonMyTariffUp() {
        binding?.bottomMyTariffs?.visibility = View.GONE
        binding?.rollUp?.visibility = View.GONE
    }

    fun onButtonMyAutoUp() {

    }

}

