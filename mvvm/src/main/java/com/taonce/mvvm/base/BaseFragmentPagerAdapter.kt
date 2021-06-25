package com.taonce.mvvm.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author: Taonce
 * @description:
 */
class BaseFragmentPagerAdapter(
    fm: FragmentManager,
    private val fragments: List<Fragment>,
    private val titles: List<String>? = null,
    behavior: Int = 0
) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = titles?.get(position) ?: ""
}

