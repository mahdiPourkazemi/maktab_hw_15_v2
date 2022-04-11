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
    var clickListener: ((Int) -> Unit)? =null

/*        init {
            setHasStableIds(true)
        }*/
    val mList = mutableListOf<City>()


    fun removeAt(position: Int) {
        mList.removeAt(position)
        notifyItemRemoved(position)
        //notifyItemMoved(position+1,position)
    }

    override fun getItemCount()=mList.size

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
        //holder.mBind(getItem(position))
        holder.mBind(mList.get(position))
        holder.itemView.setOnClickListener {
            Log.d("test", "$position is clicked")
            if (mList.get(position).isSelected) {
                holder.itemView.setBackgroundColor(
                    Color.parseColor("#FFFFFF")
                )
            } else {
                holder.itemView.setBackgroundColor(
                    Color.parseColor("#CCCCCC")
                )
            }

            clickListener?.invoke(position)
        }
    }
}

class ItemDiffUtil : DiffUtil.ItemCallback<City>() {

    override fun areItemsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean = oldItem.id == newItem.id
}