package com.pourkazemi.mahdi.maktab_hw_15_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pourkazemi.mahdi.maktab_hw_15_v2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // If size of the all items are equal and won't change for a better performance it's better to set setHasFixedSize to true
        //binding.rv.setHasFixedSize(true)

        // Creating an instance of our NameAdapter class and setting it to our RecyclerView
        val itemListAdapter = ItemListAdapter()
        binding.rv.adapter = itemListAdapter
        // Setting our RecyclerView's layout manager equal to LinearLayoutManager
        binding.rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        itemListAdapter.submitList(
            listOf(
                City(0, "isfahan", false),
            City(1,"tehran",true),
            City(2,"semnan",false)
            ).toMutableList()
        )

        // Initializing itemListAdapter.itemClickListener
        itemListAdapter.clickListener = { city, position ->
           city.isSelected=!city.isSelected
            Toast.makeText(
                this,
                "position: $position - name: $city.name",
                Toast.LENGTH_SHORT
            )
                .show()
        }
//__________________________________________________

        val swipeHandler = object : MyItemTouchHelper() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.rv.adapter as ItemListAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rv)
    }

}