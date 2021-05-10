package ru.taksi.pro.android.domain.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.ItemMainTariffsBinding
import ru.taksi.pro.android.mvvm.model.entity.tariffs.Tariff

class RVAdapterMainTariff(private val context: Context) :
    RecyclerView.Adapter<RVAdapterMainTariff.ViewHolder>() {
    lateinit var adapterBinding: ItemMainTariffsBinding

    private val data: MutableList<Tariff> = mutableListOf()

    fun setData(data: List<Tariff>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMainTariffsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tariff: Tariff) {
            when (tariff.id) {
                1 -> binding.image.setImageResource(R.drawable.tariff_icon_start)
                2 -> binding.image.setImageResource(R.drawable.tariff_icon_comfort)
                3 -> binding.image.setImageResource(R.drawable.tariff_icon_prem)
            }
            binding.name.text = tariff.name
            binding.pay.text = "${tariff.subscription} р. в месяц"
            binding.tax.text = "${tariff.rate} % от суммы вывода"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding =
            ItemMainTariffsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}