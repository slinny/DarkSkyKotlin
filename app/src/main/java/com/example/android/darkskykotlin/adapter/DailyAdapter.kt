package com.example.android.darkskykotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.darkskykotlin.databinding.ListItemDailyBinding
import com.example.android.darkskykotlin.vo.DailyData

class DailyAdapter : ListAdapter<DailyData, DailyAdapter.DailyViewHolder>(
    DailyDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DailyViewHolder private constructor(val binding: ListItemDailyBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: DailyData) {
            binding.dailyWeatherData = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DailyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemDailyBinding.inflate(layoutInflater, parent, false)
                return DailyViewHolder(binding)
            }
        }
    }

}

class DailyDiffCallback : DiffUtil.ItemCallback<DailyData>() {

    override fun areItemsTheSame(oldItem: DailyData, newItem: DailyData): Boolean {
        return oldItem.dailyId == newItem.dailyId
    }


    override fun areContentsTheSame(oldItem: DailyData, newItem: DailyData): Boolean {
        return oldItem == newItem
    }
}