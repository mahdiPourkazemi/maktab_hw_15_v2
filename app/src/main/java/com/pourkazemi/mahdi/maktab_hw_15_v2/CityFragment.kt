package com.pourkazemi.mahdi.maktab_hw_15_v2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pourkazemi.mahdi.maktab_hw_15_v2.databinding.FragmentCityBinding
import com.pourkazemi.mahdi.maktab_hw_15_v2.listadapter.ItemListAdapter


class CityFragment : Fragment(R.layout.fragment_city) {
    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!
    private val myViewModel by activityViewModels<MyViewModel>()
    private val cityItemListAdapter = ItemListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCityBinding.bind(view)
        binding.cityRv.apply {
            setHasFixedSize(true)
            adapter = cityItemListAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        myViewModel.listOfCity.observe(viewLifecycleOwner) {
            Log.d("test", "this is observed")
            cityItemListAdapter.mList.removeAll(it)
            cityItemListAdapter.mList.addAll(it)
        }

        cityItemListAdapter.clickListener = {  position ->
            //city.isSelected = !city.isSelected
            myViewModel.updateListCity(position)
            Toast.makeText(
                requireContext(),
                "position: $position",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}