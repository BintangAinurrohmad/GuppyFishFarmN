package com.example.guppyfishfarm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.guppyfishfarm.R
import com.example.guppyfishfarm.model.farm

class FarmListAdapter(

    private val onItemClickListener: (farm) -> Unit
): ListAdapter<farm, FarmListAdapter.FarmViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmViewHolder {
        return FarmViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FarmViewHolder, position: Int) {
        val farm = getItem(position)
        holder.bind(farm)
        holder.itemView.setOnClickListener {
            onItemClickListener(farm)
        }
    }
    class FarmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nametextView)
        private val addressTextView: TextView = itemView.findViewById(R.id.addresstextView)
        private val speciesTextView: TextView = itemView.findViewById(R.id.speciestextView)

        fun bind(farm: farm?) {
            nameTextView.text = farm?.name
            addressTextView.text = farm?.address
            speciesTextView.text = farm?.species
        }

        companion object {
            fun create(parent: ViewGroup): FarmListAdapter.FarmViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_farm, parent, false)
                return FarmViewHolder(view)
            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<farm>() {
            override fun areItemsTheSame(oldItem: farm, newItem: farm): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: farm, newItem: farm): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}