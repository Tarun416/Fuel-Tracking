package com.track.fueltracking.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Tarun on 11/25/17.
 */
class PagerAdapter(fm: FragmentManager, private var mNumOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> {
                PriceFragment()
            }
            1 -> {
                HistoryFragment()
            }

            else -> null
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}