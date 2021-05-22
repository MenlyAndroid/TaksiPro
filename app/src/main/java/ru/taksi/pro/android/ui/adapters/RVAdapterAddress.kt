package ru.taksi.pro.android.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.taksi.pro.android.databinding.ItemAddressListBinding
import ru.taksi.pro.android.ui.view.IAddressChanger

class RVAdapterAddress(private val context: Context, private val addressChanger: IAddressChanger) :
    RecyclerView.Adapter<RVAdapterAddress.ViewHolder>() {
    lateinit var adapterBinding: ItemAddressListBinding

    private val data: MutableList<String> = mutableListOf()

    fun setData(data: MutableList<String>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAddressListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(address: String) {
            binding.address.text = address
            binding.address.setOnClickListener {
                addressChanger.changeAddress(address)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding =
            ItemAddressListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}