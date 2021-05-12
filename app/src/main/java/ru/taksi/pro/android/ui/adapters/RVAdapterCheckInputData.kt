package ru.taksi.pro.android.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.taksi.pro.android.databinding.ItemCheckInputDataListBinding

class RVAdapterCheckInputData(private val context: Context) :
    RecyclerView.Adapter<RVAdapterCheckInputData.ViewHolder>() {
    lateinit var adapterBinding: ItemCheckInputDataListBinding

    private val data: MutableList<Int> = mutableListOf()

    fun setData(data: MutableList<Int>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCheckInputDataListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(int: Int) {
            binding.image.setImageResource(int)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding =
            ItemCheckInputDataListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }
}