package ru.taksi.pro.android.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.taksi.pro.android.databinding.ItemChoiceTariffBinding
import ru.taksi.pro.android.mvvm.model.entity.tariffs.Tariff

class RVAdapterChoiceTariff(private val context: Context) :
    RecyclerView.Adapter<RVAdapterChoiceTariff.ViewHolder>() {
    lateinit var adapterBinding: ItemChoiceTariffBinding

    val data: MutableList<Tariff> = mutableListOf()
    var checkedName = ""

    fun setData(data: MutableList<Tariff>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun setTariff(name: String) {
        checkedName = name
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemChoiceTariffBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding =
            ItemChoiceTariffBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text = data[position].name
        holder.binding.pay.text = "${data[position].subscription} р. в месяц"
        holder.binding.tax.text = "${data[position].rate} % от суммы вывода"
        holder.binding.description.text = data[position].description
        if (data[position].name == checkedName) {
            holder.binding.description.visibility = View.VISIBLE
            holder.binding.checkBox.isChecked = true
        } else {
            holder.binding.description.visibility = View.GONE
            holder.binding.checkBox.isChecked = false
        }
        holder.binding.window.setOnClickListener {
            setTariff(data[position].name)
        }
        holder.binding.checkBox.setOnClickListener{
            setTariff(data[position].name)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}