package com.tools.banner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.tools.banner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val title = arrayOf("Tab1", "Tab2", "Tab3")
        title.forEach {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it))
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.setCurrentItem(tab.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.tabLayout.setScrollPosition(position, positionOffset, true)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.viewPager.adapter = HomeAdapter(this, title.size)

    }

    class HomeAdapter(fragmentActivity: FragmentActivity, private val size: Int) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return size
        }

        override fun createFragment(position: Int): Fragment {
            return Fragment()
        }
    }
}