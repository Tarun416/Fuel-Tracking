package com.track.fueltracking.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import java.util.ArrayList

/**
 * Created by Tarun on 11/25/17.
 */
class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val mFragmentList = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment? {
        return  mFragmentList[position]
    }

    fun addFrag(fragment: Fragment) {
        mFragmentList.add(fragment) }


    override fun getCount(): Int {
        return mFragmentList.size
    }
}