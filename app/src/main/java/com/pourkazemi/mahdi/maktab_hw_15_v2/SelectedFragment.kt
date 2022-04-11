package com.pourkazemi.mahdi.maktab_hw_15_v2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pourkazemi.mahdi.maktab_hw_15_v2.databinding.FragmentSelectedBinding


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

        myViewModel.listOfSelectedCity.observe(viewLifecycleOwner) {list->

            selectedItemListAdapter.mList.addAll(list.filter { it.isSelected })//.toSet())
            Log.d("test","observed")
        }
/*        selectedItemListAdapter.clickListener = { city, position ->
            //city.isSelected = !city.isSelected
            myViewModel.updateListCity(position)
            Toast.makeText(
                requireContext(),
                "position: $position - name: $city.name",
                Toast.LENGTH_SHORT
            )
                .show()
        }*/

        val swipeHandler = object : MyItemTouchHelper() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.selectedRv.adapter as ItemListAdapter
                myViewModel.updateListCity(viewHolder.adapterPosition)
                //remove second observer list
                myViewModel.removeOfSelectedList(viewHolder.adapterPosition)
                adapter.removeAt(viewHolder.adapterPosition)
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