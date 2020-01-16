package com.ly.autoscrolllayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ly.autoscrolllayout.fragment.ScrollLayoutFragment
import com.ly.autoscrolllayout.fragment.ViewFlipperFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ScrollLayoutFragment.getNewInstance()
        1 -> ViewFlipperFragment.getNewInstance()
        else -> Fragment()
    }

    override fun getCount(): Int = 2
}