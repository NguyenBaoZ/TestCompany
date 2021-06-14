package com.example.companymanagement.viewcontroller.fragment.employe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.companymanagement.R
import com.example.companymanagement.viewcontroller.adapter.EmployeViewPagerApdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class EmployeManager : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_tablayout_pagerviewer, container, false)
        val pager = root.findViewById<ViewPager2>(R.id.employee_manager_viewpager)
        val tablayout = root.findViewById<TabLayout>(R.id.employee_manager_tab_layout)
        pager.adapter = EmployeViewPagerApdapter(this);
        var tabtext = resources.getStringArray(R.array.employee_manager_tab_name);
        TabLayoutMediator(tablayout, pager) { tab, pos -> tab.text = tabtext[pos] }.attach()
        return root;
    }


}