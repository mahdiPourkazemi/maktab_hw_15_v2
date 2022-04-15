package com.pourkazemi.mahdi.maktab_hw_15_v2

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pourkazemi.mahdi.maktab_hw_15_v2.databinding.FragmentSelectedBinding
import com.pourkazemi.mahdi.maktab_hw_15_v2.listadapter.ItemListAdapter
import com.pourkazemi.mahdi.maktab_hw_15_v2.listadapter.MyItemTouchHelper


class SelectedFragment : Fragment(R.layout.fragment_selected) {
    private val myViewModel:MyViewModel by activityViewModels()
    private var _binding: FragmentSelectedBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSelectedBinding.bind(view)

        val selectedItemListAdapter = ItemListAdapter()
        binding.selectedRv.apply {
            setHasFixedSize(true)
            adapter = selectedItemListAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        myViewModel.listOfSelectedCity.observe(viewLifecycleOwner) {

            selectedItemListAdapter.submitList(it.filter { it.isSelected }.toMutableList())
            //selectedItemListAdapter.mList.addAll(list.filter { it.isSelected })//.toSet())
            Log.d("test","observed")
        }

        val swipeHandler = object : MyItemTouchHelper() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.selectedRv.adapter as ItemListAdapter

                Log.d("test","${adapter.currentList[viewHolder.layoutPosition].id}")
                Log.d("test","${viewHolder.adapterPosition}")

                var pos = adapter.getCurrentList()[viewHolder.layoutPosition].id
                myViewModel.changeSelection(pos)
                myViewModel.removeOfSelectedList(viewHolder.layoutPosition)
                adapter.notifyItemRemoved(viewHolder.layoutPosition)
                //adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.selectedRv)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}