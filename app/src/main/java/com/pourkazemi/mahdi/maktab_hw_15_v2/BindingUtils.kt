package com.pourkazemi.mahdi.maktab_hw_15_v2

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("citySelection")
fun ConstraintLayout.isCitySelect(city: City){
if (city.isSelected) {
    setBackgroundColor(Color.parseColor("#CCCCCC"))
}else {
    setBackgroundColor(Color.parseColor("#FFFFFF"))
}

}