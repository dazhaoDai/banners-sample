package com.tools.banner

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tools.banner.databinding.ItemHolderBinding
import com.tools.banner.databinding.LayoutFragmentBinding
import com.tools.library.banner.viewpager.BannerIndicatorHelper
import com.tools.library.banner.viewpager.BannerPlayController

class Fragment: Fragment() {

    private lateinit var binding: LayoutFragmentBinding
    private val colors =
        arrayOf(Color.DKGRAY, Color.YELLOW, Color.BLUE, Color.CYAN, Color.GREEN, Color.LTGRAY, Color.MAGENTA)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val adapter = BannerAdapter(colors)
        binding.banner.setAdapter(adapter)
        binding.pagerIndicator.setCount(adapter.itemCount)
        BannerIndicatorHelper.bindViewPager(binding.pagerIndicator, binding.banner)
        BannerPlayController.add(viewLifecycleOwner, binding.banner)
    }

    class BannerAdapter(private val colors: Array<Int>) :
        RecyclerView.Adapter<BannerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            return BannerViewHolder(
                ItemHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
            holder.bind(colors[position])
        }

        override fun getItemCount(): Int {
            return colors.size
        }
    }

    class BannerViewHolder(private val binding: ItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(@ColorRes color: Int) {
            binding.ivImage.setBackgroundColor(color)
        }
    }

}