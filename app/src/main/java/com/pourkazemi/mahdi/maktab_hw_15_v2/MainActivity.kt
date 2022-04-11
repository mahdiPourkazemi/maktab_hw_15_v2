package com.pourkazemi.mahdi.maktab_hw_15_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pourkazemi.mahdi.maktab_hw_15_v2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val mNavHostFragment =
            supportFragmentManager.findFragmentById(
                R.id.fragmentContainerView
            ) as NavHostFragment

        //val mNavHostFragment=binding.fragmentContainerView as NavHostFragment

        binding.bottomNavigationView.setupWithNavController(mNavHostFragment.navController)

        setContentView(binding.root)

    }

}