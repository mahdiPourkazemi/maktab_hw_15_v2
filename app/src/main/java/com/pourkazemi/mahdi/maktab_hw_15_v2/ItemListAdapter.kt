package com.pourkazemi.mahdi.maktab_hw_15_v2

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pourkazemi.mahdi.maktab_hw_15_v2.databinding.MyItemBinding


class ItemListAdapter : ListAdapter<City, ItemListAdapter.ItemViewHolder>(ItemDiffUtil()) {
    var clickListener: ((city: City, position: Int) -> Unit?)? = null
    init {
        setHasStableIds(true)
    }
    fun removeAt(position: Int) {
        //currentList.removeAt(position)
        notifyItemRemoved(position)
    }

    class ItemViewHolder(
        private val binding: MyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun mBind(city: City) {
            binding.city = city
        }

        companion object {
            fun create(
                inflater: LayoutInflater,
                layoutId: Int,
                parent: ViewGroup?,
                attachToRoot: Boolean
            ) = ItemViewHolder(
                DataBindingUtil.inflate(
                    //MyItemBinding.inflate(
                    inflater,
                    layoutId,
                    parent,
                    attachToRoot
                )
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder.create(
        LayoutInflater.from(parent.context),
        R.layout.my_item,
        parent,
        false
    )


    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.mBind(getItem(position))
        holder.itemView.setOnClickListener {
            Log.d("test", "$position is clicked")
            if (getItem(position).isSelected) {
                holder.itemView.setBackgroundColor(
                    Color.parseColor("#FFFFFF")
                )
            } else {
                holder.itemView.setBackgroundColor(
                    Color.parseColor("#CCCCCC")
                )
            }

            clickListener?.invoke(getItem(position), position)
        }
    }
}

class ItemDiffUtil : DiffUtil.ItemCallback<City>() {

    override fun areItemsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean = oldItem.name == newItem.name
}